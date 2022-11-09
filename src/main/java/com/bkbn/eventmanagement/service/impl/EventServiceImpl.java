package com.bkbn.eventmanagement.service.impl;

import com.bkbn.eventmanagement.dto.OpenWeatherResponse;
import com.bkbn.eventmanagement.exception.NotFoundException;
import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.repository.EventRepository;
import com.bkbn.eventmanagement.service.EventService;
import com.bkbn.eventmanagement.service.RestClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final RestClient restClient;

    public EventServiceImpl(EventRepository eventRepository, RestClient restClient) {
        this.eventRepository = eventRepository;
        this.restClient = restClient;
    }

    @Override
    public CompletableFuture<Event> saveEvent(Event event) {
        return CompletableFuture.completedFuture(event)
                .thenApply(e -> {
                    OpenWeatherResponse weatherData = restClient.getWeatherData(e.getCity());
                    e.setWeather(weatherData.getMain());
                    return e;
                })
                .thenApply(eventRepository::save);

    }

    @Override
    public Event findEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(()-> new NotFoundException("Event not found"));
    }
}
