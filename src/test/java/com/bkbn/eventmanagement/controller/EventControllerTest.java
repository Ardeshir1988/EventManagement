package com.bkbn.eventmanagement.controller;

import com.bkbn.eventmanagement.exception.NotFoundException;
import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.model.EventGuest;
import com.bkbn.eventmanagement.model.EventType;
import com.bkbn.eventmanagement.model.Weather;
import com.bkbn.eventmanagement.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
    @MockBean
    EventService eventService;

    @Autowired
    private MockMvc mockMvc;


    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createNewEvent() throws Exception {
        var weather = Weather.builder().temp(5.5).humidity(55).build();
        var event = getSampleEvent(1,EventType.CONCERT,"coldplay","berlin","Germany",weather,List.of());
        when(eventService.saveEvent(event)).thenReturn(CompletableFuture.completedFuture(event));
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(event)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("coldplay"))
                .andExpect(jsonPath("$.city").value("berlin"));
    }

    @Test
    public void createNewEvent_withoutName_BadRequest() throws Exception {
        var weather = Weather.builder().temp(5.5).humidity(55).build();
        var event = getSampleEvent(1,EventType.CONCERT,"coldplay","berlin","Germany",weather,List.of());
        // set null
        event.setName(null);

        when(eventService.saveEvent(event)).thenReturn(CompletableFuture.completedFuture(event));

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(event)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Event name is mandatory"));
    }


    @Test
    void findEventById_event1() throws Exception {
        var weather = Weather.builder().temp(5.5).humidity(55).build();
        var event = getSampleEvent(1,EventType.CONCERT,"coldplay","berlin","Germany",weather,List.of());

        when(eventService.findEventById(1)).thenReturn(event);
        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("coldplay"))
                .andExpect(jsonPath("$.city").value("berlin"));
    }
    @Test
    void findEventById_notExisted_thrownNotFoundException() throws Exception {
        when(eventService.findEventById(1)).thenThrow(new NotFoundException("Event not found"));
        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Event not found"));
    }

    @Test
    void deleteEventById_event1() throws Exception {
        var weather = Weather.builder().temp(5.5).humidity(55).build();
        var event = getSampleEvent(1,EventType.CONCERT,"coldplay","berlin","Germany",weather,List.of());

        when(eventService.findEventById(1)).thenReturn(event);
        mockMvc.perform(delete("/api/events/1"))
                .andExpect(status().isNoContent());
    }

    private Event getSampleEvent(Integer eventId,
                                 EventType eventType,
                                 String eventName,
                                 String city,
                                 String country,
                                 Weather weather,
                                 List<EventGuest> guestList){
        return Event.builder()
                .id(eventId)
                .name(eventName)
                .city(city)
                .country(country)
                .date(new Date())
                .weather(weather)
                .eventType(eventType)
                .guestList(guestList)
                .build();
    }
}
