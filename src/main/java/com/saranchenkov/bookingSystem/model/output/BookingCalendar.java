package com.saranchenkov.bookingSystem.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingCalendar)) return false;

        BookingCalendar that = (BookingCalendar) o;

        if (!meetingDate.equals(that.meetingDate)) return false;
        return bookings.equals(that.bookings);
    }

    @Override
    public int hashCode() {
        int result = meetingDate.hashCode();
        result = 31 * result + bookings.hashCode();
        return result;
    }
}