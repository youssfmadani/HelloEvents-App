package com.example.helloevents.mapper;

import org.mapstruct.Mapper;
import com.example.helloevents.models.Event;
import com.example.helloevents.dto.EventDTO;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);
    Event toEntity(EventDTO eventDTO);
}
