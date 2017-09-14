package com.saranchenkov.bookingSystem.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.saranchenkov.bookingSystem.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceImplTest {

    @Autowired
    private BookingService service;

    @Before
    public void setUp() throws Exception {
        service.setBatch(BATCH);
    }

    @After
    public void tearDown() throws Exception {
        service.setBatch(null);
    }

    @Test
    public void getCalendars() throws Exception {
        Assert.assertEquals(CALENDARS, service.getCalendars());
    }
}