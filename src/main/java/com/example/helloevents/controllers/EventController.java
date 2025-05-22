package com.example.helloevents.controllers;

import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.models.Event;
import com.example.helloevents.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Optional<EventDTO> eventDTO = eventService.getEventById(id);
        return eventDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/search")
    public List<EventDTO> searchEvents(@RequestParam(required = false) String category,
                                    @RequestParam(required = false) String location,
                                    @RequestParam(required = false) String keyword) {
        if (category != null) return eventService.searchByCategory(category);
        if (location != null) return eventService.searchByLocation(location);
        if (keyword != null) return eventService.searchByKeyword(keyword);
        return eventService.getAllEvents();
    }
}
