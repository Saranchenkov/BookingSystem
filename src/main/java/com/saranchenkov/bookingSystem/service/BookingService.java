package com.saranchenkov.bookingSystem.service;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 13.09.2017.
 */
@Service
public class BookingService {

    private Batch batch;

    public void setBatch(Batch batch) {
        this.batch = batch;
    }


    public List<BookingCalendar> getCalendars(){
        List<BookingRequest> successfulRequests = getSuccessfulRequests();

        Map<LocalDate, List<BookingRequest>> map = successfulRequests
                .stream()
                .collect(Collectors.groupingBy(BookingRequest::getMeetingDate, Collectors.toList()));

        List<BookingCalendar> calendars = map.entrySet()
                                                .stream()
                                                .map(entry -> new BookingCalendar(entry.getKey(), convertToBookingList(entry.getValue())))
                                                .sorted(Comparator.comparing(BookingCalendar::getMeetingDate))
                                                .collect(Collectors.toList());
        return calendars;
    }

    private List<BookingRequest> getSuccessfulRequests(){
        List<BookingRequest> requests = batch.getRequests();
        requests.sort(Comparator.comparing(BookingRequest::getSubmissionTime));
        List<BookingRequest> successfulRequests = new ArrayList<>();

       outer:
       for (BookingRequest request : requests) {
           if (checkFallOutsideOfficeHours(request)) continue;
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

    // return true if request fall outside office hours, and false if not
    private boolean checkFallOutsideOfficeHours(BookingRequest request){
        LocalTime meetingStartTime = request.getMeetingStartTime().toLocalTime();
        LocalTime meetingEndTime = meetingStartTime.plusHours(request.getDuration());

        return !(isBetween(meetingStartTime, batch) && isBetween(meetingEndTime, batch));
    }

    private boolean isBetween(LocalTime time, Batch batch){
        return time.compareTo(batch.getStart()) >= 0 && time.compareTo(batch.getEnd()) <= 0;
    }

    private boolean nonOverlap(BookingRequest request1, BookingRequest request2){
        LocalTime startTime1 = request1.getMeetingStartTime().toLocalTime();
        LocalTime endTime1 = startTime1.plusHours(request1.getDuration());

        LocalTime startTime2 = request2.getMeetingStartTime().toLocalTime();
        LocalTime endTime2 = startTime2.plusHours(request2.getDuration());

        return endTime1.compareTo(startTime2) <= 0 || startTime1.compareTo(endTime2) >= 0;
    }

    private List<Booking> convertToBookingList(List<BookingRequest> requests){
        return requests.stream()
                .map(BookingRequest::convertToBooking)
                .sorted(Comparator.comparing(Booking::getStartTime))
                .collect(Collectors.toList());
    }

}
