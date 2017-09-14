package com.saranchenkov.bookingSystem.util;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class DateTimeUtil {

    private DateTimeUtil(){}

    /**
     * Converts list of {@link BookingRequest} to list of {@link Booking}
     * and sorts in chronological order by {@link Booking#startTime}
     *
     * @param requests list that needs to be converted
     * @return sorted list of {@link Booking}
     */
    public static List<Booking> convertToSortedBookingList(List<BookingRequest> requests){
        return requests.stream()
                .map(Booking::new)
                .sorted(Comparator.comparing(Booking::getStartTime))
                .collect(Collectors.toList());
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

        return endTime1.compareTo(startTime2) <= 0 || startTime1.compareTo(endTime2) >= 0;
    }

    /**
     * Checks whether the {@code meetingTime} is between working time start and end.
     *
     * @param meetingTime {@code LocalTime} object to be checked
     * @param batch {@link Batch} object that contains working time start and
     *        end as {@link LocalTime} objects
     * @return {@code true} if during office hours and {@code false} otherwise
     */
    public static boolean isBetween(LocalTime meetingTime, Batch batch){
        return meetingTime.compareTo(batch.getStart()) >= 0 && meetingTime.compareTo(batch.getEnd()) <= 0;
    }

    /**
     * Checks whether the meeting time of this {@code request} falls outside office hours.
     *
     * @param request {@code BookingRequest} object to be checked
     * @param batch {@link Batch} object that contains working time start and
     *        end as {@link LocalTime} objects
     * @return {@code true} if some part of meeting falls outside office hours and
     *         {@code false} otherwise
     */
    public static boolean checkFallOutsideOfficeHours(BookingRequest request, Batch batch){
        LocalTime meetingStartTime = request.getMeetingStartTime().toLocalTime();
        LocalTime meetingEndTime = meetingStartTime.plusHours(request.getDuration());

        return !(isBetween(meetingStartTime, batch) && isBetween(meetingEndTime, batch));
    }
}
