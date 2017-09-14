package com.saranchenkov.bookingSystem.util;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;

import java.time.LocalTime;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public final class BookingUtil {

    private BookingUtil(){}

    /**
     * Converts list of {@link BookingRequest} to list of {@link Booking}
     *
     * @param requests list to be converted
     * @return list of {@link Booking}
     */
    public static List<Booking> convertToBookingList(List<BookingRequest> requests){
        return requests.stream()
                .map(Booking::new)
                .collect(Collectors.toList());
    }

    /**
     * Checks whether the meeting time of this {@link BookingRequest} falls outside office hours.
     *
     * @param request {@code BookingRequest} object to be checked
     * @param batch {@link Batch} object that contains working time start and
     *        end as {@link LocalTime} objects
     * @return {@code true} if no part of a meeting falls outside office hours and
     *         {@code false} otherwise
     */
    public static boolean nonFallOutsideOfficeHours(BookingRequest request, Batch batch){
        LocalTime meetingStartTime = request.getMeetingStartTime().toLocalTime();
        LocalTime meetingEndTime = meetingStartTime.plusHours(request.getDuration());

        return meetingStartTime.compareTo(batch.getStart()) >= 0 && meetingEndTime.compareTo(batch.getEnd()) <= 0;
    }

    /**
     * Removes all booking requests that may overlap
     *
     * @param requests list of booking requests
     */
    public static void removeBookingsWithOverlaps(List<BookingRequest> requests){
        ListIterator<BookingRequest> iterator = requests.listIterator(1);
        BookingRequest current = requests.get(0);
        while (iterator.hasNext()){
            BookingRequest next = iterator.next();
            if (nonOverlap(current, next)){
                current = next;
            } else {
                iterator.remove();
            }
        }
    }

    /**
     * Checks two {@link BookingRequest} objects for overlap of meeting time
     *
     * @param request1 the first {@code BookingRequest} object
     * @param request2 the second {@code BookingRequest} object to be checked for overlap
     *        with {@code request1}
     * @return {@code true} if overlap is not exist and {@code false} otherwise
     */
    public static boolean nonOverlap(BookingRequest request1, BookingRequest request2){
        if (!request1.getMeetingDate().equals(request2.getMeetingDate())) return true;

        LocalTime startTime1 = request1.getMeetingStartTime().toLocalTime();
        LocalTime endTime1 = startTime1.plusHours(request1.getDuration());

        LocalTime startTime2 = request2.getMeetingStartTime().toLocalTime();
        LocalTime endTime2 = startTime2.plusHours(request2.getDuration());

        return endTime1.compareTo(startTime2) <= 0 || endTime2.compareTo(startTime1) <= 0;
    }


}
