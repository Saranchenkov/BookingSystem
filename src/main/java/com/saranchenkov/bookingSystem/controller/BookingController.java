package com.saranchenkov.bookingSystem.controller;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;
import com.saranchenkov.bookingSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookingCalendar>> getCalendars(){
        return ResponseEntity.ok(service.getCalendars());
    }

    @PostMapping
    public ResponseEntity<?> putBatch(@RequestBody Batch batch){
        service.setBatch(batch);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}