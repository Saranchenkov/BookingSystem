package com.saranchenkov.bookingSystem.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saranchenkov.bookingSystem.model.output.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Ivan on 13.09.2017.
 */

@Component
public class BookingRequest {
    private String employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submissionTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime meetingStartTime;
    private int duration;

    public BookingRequest(){}

    public BookingRequest(String employeeId, LocalDateTime submissionTime, LocalDateTime meetingStartTime, int duration) {
        this.employeeId = employeeId;
        this.submissionTime = submissionTime;
        this.meetingStartTime = meetingStartTime;
        this.duration = duration;
    }

    public LocalDate getMeetingDate(){
        return meetingStartTime.toLocalDate();
    }

    public Booking convertToBooking(){
        return new Booking(employeeId, meetingStartTime.toLocalTime(), meetingStartTime.toLocalTime().plusHours(duration));
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public LocalDateTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(LocalDateTime meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
