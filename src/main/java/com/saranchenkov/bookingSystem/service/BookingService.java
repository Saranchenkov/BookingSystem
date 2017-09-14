package com.saranchenkov.bookingSystem.service;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;

import java.util.List;

public interface BookingService {

    void setBatch(Batch batch);

    /**
     * Returns list of successful {@link BookingCalendar} with {@link Booking}
     * being grouped chronologically by day.
     *
     * @return list of successful {@link BookingCalendar} with bookings being
     *         grouped chronologically by day.
     */
    List<BookingCalendar> getCalendars();

    /**
     * Returns list of successful {@link BookingRequest}
     * The booking request is successful under the following conditions:
     * - No part of a meeting may fall outside office hours
     * - Meetings may not overlap
     *
     * @return list of succesfull {@link BookingRequest}
     */
    List<BookingRequest> getSuccessfulRequests();
}
