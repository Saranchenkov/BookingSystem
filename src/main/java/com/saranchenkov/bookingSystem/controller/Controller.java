package com.saranchenkov.bookingSystem.controller;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;
import com.saranchenkov.bookingSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ivan on 13.09.2017.
 */
@RestController
public class Controller {

    private final BookingService service;

    @Autowired
    public Controller(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookingCalendar> getCalendars(){
        return service.getCalendars();
    }

    @PostMapping
    public void putBatch(@RequestBody Batch batch){
        service.setBatch(batch);
    }
}