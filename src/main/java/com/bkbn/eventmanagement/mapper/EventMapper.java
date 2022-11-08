package com.bkbn.eventmanagement.mapper;

import com.bkbn.eventmanagement.dto.EventDto;
import com.bkbn.eventmanagement.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto eventDto);
}
