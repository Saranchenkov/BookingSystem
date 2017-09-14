package com.saranchenkov.bookingSystem.controller;

import com.saranchenkov.bookingSystem.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.saranchenkov.bookingSystem.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService service;

    @Test
    public void getCalendars() throws Exception {
        when(service.getCalendars()).thenReturn(CALENDARS);
        MvcResult result = mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn();

        JSONAssert.assertEquals(CALENDARS_AS_JSON, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void putBatch() throws Exception {
        mvc.perform(post("/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(BATCH_AS_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

    }

}