package com.bkbn.eventmanagement.service.impl;

import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.repository.EventRepository;
import com.bkbn.eventmanagement.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
