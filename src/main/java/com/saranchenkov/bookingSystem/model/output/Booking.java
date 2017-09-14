package com.saranchenkov.bookingSystem.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;

import java.time.LocalTime;

public class Booking {

    String employeeId;

    @JsonFormat(pattern = "HH:mm")
    LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    LocalTime endTime;

    public Booking(){}

    public Booking(String employeeId, LocalTime startTime, LocalTime endTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Booking(BookingRequest request){
        this.employeeId = request.getEmployeeId();
        this.startTime = request.getMeetingStartTime().toLocalTime();
        this.endTime = startTime.plusHours(request.getDuration());
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (!employeeId.equals(booking.employeeId)) return false;
        if (!startTime.equals(booking.startTime)) return false;
        return endTime.equals(booking.endTime);
    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        return result;
    }
}