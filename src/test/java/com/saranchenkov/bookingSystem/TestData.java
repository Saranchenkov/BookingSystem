package com.saranchenkov.bookingSystem;

import com.saranchenkov.bookingSystem.model.input.Batch;
import com.saranchenkov.bookingSystem.model.input.BookingRequest;
import com.saranchenkov.bookingSystem.model.output.Booking;
import com.saranchenkov.bookingSystem.model.output.BookingCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final LocalTime START = LocalTime.of(9, 0);
    public static final LocalTime END = LocalTime.of(17, 30);

    public static final BookingRequest REQUEST_1 = new BookingRequest("EMP001",
            LocalDateTime.of(2011, 3, 17, 10, 17, 6),
            LocalDateTime.of(2011, 3, 21, 9, 0), 2);

    public static final BookingRequest REQUEST_2 = new BookingRequest("EMP002",
            LocalDateTime.of(2011, 3, 16, 12, 34, 56),
            LocalDateTime.of(2011, 3, 21, 9, 0), 2);

    public static final BookingRequest REQUEST_3 = new BookingRequest("EMP003",
            LocalDateTime.of(2011, 3, 16, 9, 28, 23),
            LocalDateTime.of(2011, 3, 22, 14, 0), 2);

    public static final BookingRequest REQUEST_4 = new BookingRequest("EMP004",
            LocalDateTime.of(2011, 3, 17, 11, 23, 45),
            LocalDateTime.of(2011, 3, 22, 16, 0), 1);

    public static final BookingRequest REQUEST_5 = new BookingRequest("EMP005",
            LocalDateTime.of(2011, 3, 15, 17, 29, 12),
            LocalDateTime.of(2011, 3, 21, 16, 0), 3);

    public static final List<BookingRequest> REQUESTS = Arrays.asList(REQUEST_1, REQUEST_2, REQUEST_3, REQUEST_4, REQUEST_5);

    public static final Batch BATCH = new Batch(START, END, REQUESTS);

    public static final List<BookingRequest> SUCCESSFUL_REQUESTS = Arrays.asList(REQUEST_3, REQUEST_2, REQUEST_4);

    public static final Booking BOOKING_1 = new Booking("EMP002", LocalTime.of(9, 0), LocalTime.of(11, 0));
    public static final Booking BOOKING_2 = new Booking("EMP003", LocalTime.of(14, 0), LocalTime.of(16, 0));
    public static final Booking BOOKING_3 = new Booking("EMP004", LocalTime.of(16, 0), LocalTime.of(17, 0));

    public static final BookingCalendar CALENDAR_1 = new BookingCalendar(LocalDate.of(2011, 3, 21), Arrays.asList(BOOKING_1));
    public static final BookingCalendar CALENDAR_2 = new BookingCalendar(LocalDate.of(2011, 3, 22), Arrays.asList(BOOKING_2, BOOKING_3));
    public static final List<BookingCalendar> CALENDARS = Arrays.asList(CALENDAR_1, CALENDAR_2);

    public static final String CALENDARS_AS_JSON =
            "[\n" +
            "  {\n" +
                "\"meetingDate\": \"2011-03-21\",\n" +
                "\"bookings\": [\n" +
                    "  {\n" +
                        "\"employeeId\": \"EMP002\",\n" +
                        "\"startTime\": \"09:00\",\n" +
                        "\"endTime\": \"11:00\"\n" +
                    "}\n" +
                "]\n" +
            "},\n" +
            "  {\n" +
                "\"meetingDate\": \"2011-03-22\",\n" +
                "\"bookings\": [\n" +
                    "  {\n" +
                        "\"employeeId\": \"EMP003\",\n" +
                        "\"startTime\": \"14:00\",\n" +
                        "\"endTime\": \"16:00\"\n" +
                    "},\n" +
                    "  {\n" +
                        "\"employeeId\": \"EMP004\",\n" +
                        "\"startTime\": \"16:00\",\n" +
                        "\"endTime\": \"17:00\"\n" +
                    "}\n" +
                "]\n" +
            "}\n" +
            "]";

    public static final String BATCH_AS_JSON =
            "{\n" +
            "\"start\": \"09:00\",\n" +
            "\"end\": \"17:30\",\n" +
            "\"requests\": [\n" +
            "  {\n" +
                    "\"employeeId\": \"EMP001\",\n" +
                    "\"submissionTime\": \"2011-03-17 10:17:06\",\n" +
                    "\"meetingStartTime\": \"2011-03-21 09:00\",\n" +
                    "\"duration\": 2,\n" +
                    "\"meetingDate\": \"2011-03-21\"\n" +
            "},\n" +
            "  {\n" +
                    "\"employeeId\": \"EMP002\",\n" +
                    "\"submissionTime\": \"2011-03-16 12:34:56\",\n" +
                    "\"meetingStartTime\": \"2011-03-21 09:00\",\n" +
                    "\"duration\": 2,\n" +
                    "\"meetingDate\": \"2011-03-21\"\n" +
            "},\n" +
            "  {\n" +
                    "\"employeeId\": \"EMP003\",\n" +
                    "\"submissionTime\": \"2011-03-16 09:28:23\",\n" +
                    "\"meetingStartTime\": \"2011-03-22 14:00\",\n" +
                    "\"duration\": 2,\n" +
                    "\"meetingDate\": \"2011-03-22\"\n" +
            "},\n" +
            "  {\n" +
                    "\"employeeId\": \"EMP004\",\n" +
                    "\"submissionTime\": \"2011-03-17 11:23:45\",\n" +
                    "\"meetingStartTime\": \"2011-03-22 16:00\",\n" +
                    "\"duration\": 1,\n" +
                    "\"meetingDate\": \"2011-03-22\"\n" +
            "},\n" +
            "  {\n" +
                    "\"employeeId\": \"EMP005\",\n" +
                    "\"submissionTime\": \"2011-03-15 17:29:12\",\n" +
                    "\"meetingStartTime\": \"2011-03-21 16:00\",\n" +
                    "\"duration\": 3,\n" +
                    "\"meetingDate\": \"2011-03-21\"\n" +
            "}\n" +
            "]\n" +
            "}";
}
