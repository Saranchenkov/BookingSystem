package com.saranchenkov.bookingSystem.service;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;
import com.saranchenkov.bookingSystem.util.BookingUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.saranchenkov.bookingSystem.util.BookingUtil.*;

@Service
public class BookingServiceImpl implements BookingService{

    /**
     * Batch of booking requests
     */
    private Batch batch;

    @Override
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * Returns list of successful {@link BookingCalendar} with {@link Booking} being
     * grouped chronologically by day.<br>
     *
     * Step 1: filter requests that fall outside office hours, sort by
     *         {@link BookingRequest#submissionTime} and group chronologically
     *         by {@link BookingRequest#getMeetingDate()} <br>
     * Step 2: remove booking requests having meeting time overlap <br>
     * Step 3: convert collection to list of {@link BookingCalendar} and sort it
     *         chronologically by {@link BookingCalendar#meetingDate}
     *
     * @return list of successful {@link BookingCalendar} with bookings being
     *         grouped chronologically by day.
     * @see BookingUtil#nonFallOutsideOfficeHours(BookingRequest, Batch)
     * @see BookingUtil#removeBookingsWithOverlaps(List)
     * @see BookingUtil#convertToBookingList(List)
     */

    public List<BookingCalendar> getCalendars(){
        if (Objects.isNull(batch)) return Collections.emptyList();

        // Step 1
        Map<LocalDate, List<BookingRequest>> requestsByDate = batch.getRequests()
                .stream()
                .filter(request -> nonFallOutsideOfficeHours(request, batch))
                .sorted(Comparator.comparing(BookingRequest::getSubmissionTime))
                .collect(Collectors.groupingBy(BookingRequest::getMeetingDate, Collectors.toList()));

        // Step 2
        requestsByDate.entrySet()
                .forEach(entry -> removeBookingsWithOverlaps(entry.getValue()));

        // Step 3
        List<BookingCalendar> calendars = requestsByDate.entrySet()
                .stream()
                .map(entry -> new BookingCalendar(entry.getKey(), convertToBookingList(entry.getValue())))
                .sorted(Comparator.comparing(BookingCalendar::getMeetingDate))
                .collect(Collectors.toList());

       return calendars;
    }
}
