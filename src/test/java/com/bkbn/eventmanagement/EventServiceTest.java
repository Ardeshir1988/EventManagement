package com.bkbn.eventmanagement;

import com.bkbn.eventmanagement.model.Event;
import com.bkbn.eventmanagement.model.EventGuest;
import com.bkbn.eventmanagement.model.EventType;
import com.bkbn.eventmanagement.model.Weather;
import com.bkbn.eventmanagement.repository.EventRepository;
import com.bkbn.eventmanagement.service.EventService;
import com.bkbn.eventmanagement.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EventServiceImpl.class})
public class EventServiceTest {

    @MockBean
    EventRepository eventRepository;
    @Autowired
    EventService eventService;

    @Test
    public void saveEvent_newEvent(){
        var weather = Weather.builder().temp(5.5).humidity(55).build();
        var event = getSampleEvent(1,EventType.CONCERT,"coldplay","berlin","Germany",weather,List.of());

        when(eventRepository.save(event)).thenReturn(event);
        Event savedEvent = eventService.saveEvent(event);
        assertEquals(EventType.CONCERT,event.getEventType());
        assertEquals(5.5,savedEvent.getWeather().getTemp());
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
