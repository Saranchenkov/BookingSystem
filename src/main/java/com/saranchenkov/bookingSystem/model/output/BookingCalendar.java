package com.saranchenkov.bookingSystem.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ivan on 13.09.2017.
 */
@Component
public class BookingCalendar {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate meetingDate;

    List<Booking> bookings;

    public BookingCalendar(){}

    public BookingCalendar(LocalDate meetingDate, List<Booking> bookings) {
        this.meetingDate = meetingDate;
        this.bookings = bookings;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}