package com.example.helloevents.services;



import com.example.helloevents.models.Event;
import com.example.helloevents.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> searchByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    public List<Event> searchByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Event> searchByKeyword(String keyword) {
        return eventRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
