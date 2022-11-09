package com.bkbn.eventmanagement.controller;

import com.bkbn.eventmanagement.dto.EventDto;
import com.bkbn.eventmanagement.mapper.EventMapper;
import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService,
                           EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<EventDto> saveNewEvent(@Valid @RequestBody EventDto eventDto){
        Event event = eventMapper.toEntity(eventDto);
        return eventService.saveEvent(event)
                .thenApply(savedEvent -> eventMapper.toDto(savedEvent));
    }

    @GetMapping("/{eventId}")
    public EventDto findEventById(@PathVariable Integer eventId){
        return eventMapper.toDto(eventService.findEventById(eventId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{eventId}")
    public void deleteEventById(@PathVariable Integer eventId){
        eventService.deleteEventById(eventId);
    }

    @GetMapping()
    public List<EventDto> findAllEvents(){
        List<Event> events = eventService.findAllEvents();
        return eventMapper.toDtos(events);
    }
}
