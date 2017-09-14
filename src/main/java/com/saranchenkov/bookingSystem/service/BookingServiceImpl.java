package com.saranchenkov.bookingSystem.service;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;
import com.saranchenkov.bookingSystem.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.saranchenkov.bookingSystem.util.DateTimeUtil.*;

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
     * Groups by date and sorts in chronological order.
     * Returns list of successful {@link BookingCalendar} with {@link Booking} being
     * grouped chronologically by day.
     *
     * @return list of successful {@link BookingCalendar} with bookings being
     *         grouped chronologically by day.
     */
    @Override
    public List<BookingCalendar> getCalendars(){
        if (Objects.isNull(batch)) return Collections.emptyList();
        List<BookingRequest> successfulRequests = getSuccessfulRequests();
        Map<LocalDate, List<BookingRequest>> reqByDate = successfulRequests
                .stream()
                .collect(Collectors.groupingBy(BookingRequest::getMeetingDate, Collectors.toList()));

        List<BookingCalendar> calendars = reqByDate.entrySet()
                                                .stream()
                                                .map(entry -> new BookingCalendar(entry.getKey(), convertToSortedBookingList(entry.getValue())))
                                                .sorted(Comparator.comparing(BookingCalendar::getMeetingDate))
                                                .collect(Collectors.toList());
        return calendars;
    }

    /**
     * Returns list of successful {@link BookingRequest} sorted in chronological order
     * by request submission time.
     * The booking request is successful under the following conditions:
     * - No part of a meeting may fall outside office hours
     * - Meetings may not overlap
     *
     * @return list of succesfull {@link BookingRequest} sorted in chronological order
     *         by request submission time
     * @see DateTimeUtil#checkFallOutsideOfficeHours(BookingRequest, Batch)
     * @see DateTimeUtil#nonOverlap(BookingRequest, BookingRequest)
     */
    @Override
    public List<BookingRequest> getSuccessfulRequests(){
        if (Objects.isNull(batch)) return Collections.emptyList();

        List<BookingRequest> requests = batch.getRequests();
        requests.sort(Comparator.comparing(BookingRequest::getSubmissionTime));
        List<BookingRequest> successfulRequests = new ArrayList<>();

outer:  for (BookingRequest request : requests) {
           if (checkFallOutsideOfficeHours(request, batch)) continue;
           if (successfulRequests.isEmpty()) {
               successfulRequests.add(request);
           } else {
               for (BookingRequest successfulRequest : successfulRequests) {
                   if (!nonOverlap(request, successfulRequest)) continue outer;
               }
               successfulRequests.add(request);
           }
       }
       return successfulRequests;
    }
}
