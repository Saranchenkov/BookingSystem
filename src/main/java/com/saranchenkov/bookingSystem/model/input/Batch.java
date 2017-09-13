package com.saranchenkov.bookingSystem.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Ivan on 13.09.2017.
 */

@Component
public class Batch {

    @JsonFormat(pattern = "HH:mm")
    LocalTime start;

    @JsonFormat(pattern = "HH:mm")
    LocalTime end;

    List<BookingRequest> requests;

    public Batch(){}

    public Batch(LocalTime start, LocalTime end, List<BookingRequest> requests) {
        this.start = start;
        this.end = end;
        this.requests = requests;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public List<BookingRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<BookingRequest> requests) {
        this.requests = requests;
    }
}