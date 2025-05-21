package com.example.helloevents.controllers;



import com.example.helloevents.models.Event;
import com.example.helloevents.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam(required = false) String category,
                                    @RequestParam(required = false) String location,
                                    @RequestParam(required = false) String keyword) {
        if (category != null) return eventService.searchByCategory(category);
        if (location != null) return eventService.searchByLocation(location);
        if (keyword != null) return eventService.searchByKeyword(keyword);
        return eventService.getAllEvents();
    }
}
