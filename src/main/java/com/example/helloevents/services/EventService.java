package com.example.helloevents.services;



import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.mapper.EventMapper;
import com.example.helloevents.models.Event;
import com.example.helloevents.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        // Convert DTO to entity
        Event event = eventMapper.toEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        // Convert entity back to DTO
        return eventMapper.toDto(savedEvent);
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        // Convert list of entities to list of DTOs
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDto);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<EventDTO> searchByCategory(String category) {
        List<Event> events = eventRepository.findByCategory(category);
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    public List<EventDTO> searchByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<EventDTO> searchByKeyword(String keyword) {
        return eventRepository.findByTitleContainingIgnoreCase(keyword);

    }

    // Similar for other search methods...
}

