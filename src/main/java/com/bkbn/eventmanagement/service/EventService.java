package com.bkbn.eventmanagement.service;

import com.bkbn.eventmanagement.model.Event;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EventService {
    CompletableFuture<Event> saveEvent(Event event);
    Event findEventById(int eventId);

    void deleteEventById(int eventId);

    List<Event> findAllEvents();
}
