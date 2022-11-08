package com.bkbn.eventmanagement.controller;

import com.bkbn.eventmanagement.dto.EventDto;
import com.bkbn.eventmanagement.mapper.EventMapper;
import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService,
                           EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public EventDto saveNewEvent(@Valid @RequestBody EventDto eventDto){
        Event event = eventMapper.toEntity(eventDto);
        Event savedEvent = eventService.saveEvent(event);
        return eventMapper.toDto(savedEvent);
    }
}
