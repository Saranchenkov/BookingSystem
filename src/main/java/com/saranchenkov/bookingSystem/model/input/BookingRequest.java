package com.saranchenkov.bookingSystem.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingRequest)) return false;

        BookingRequest that = (BookingRequest) o;

        if (duration != that.duration) return false;
        if (!employeeId.equals(that.employeeId)) return false;
        if (!submissionTime.equals(that.submissionTime)) return false;
        return meetingStartTime.equals(that.meetingStartTime);
    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
        result = 31 * result + submissionTime.hashCode();
        result = 31 * result + meetingStartTime.hashCode();
        result = 31 * result + duration;
        return result;
    }
}
