package com.bkbn.eventmanagement.mapper;

import com.bkbn.eventmanagement.dto.EventDto;
import com.bkbn.eventmanagement.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto eventDto);
    List<EventDto> toDtos(List<Event> events);
}
