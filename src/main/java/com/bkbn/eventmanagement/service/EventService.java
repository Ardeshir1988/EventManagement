package com.bkbn.eventmanagement.service;

import com.bkbn.eventmanagement.model.Event;

import java.util.concurrent.CompletableFuture;

public interface EventService {
    CompletableFuture<Event> saveEvent(Event event);
}
