package com.example.helloevents.controllers;

import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.dto.UserDTO;
import com.example.helloevents.services.EventService;
import com.example.helloevents.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public AdminController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    // Récupérer tous les clients sous forme de DTOs
    @GetMapping("/clients")
    public List<UserDTO> getClients() {
        return userService.getAllUsers();
    }

    // Supprimer un client
    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    // Récupérer tous les événements sous forme de DTOs
    @GetMapping("/events")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Créer un nouvel événement avec DTO
    @PostMapping("/events")
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    // Mettre à jour un événement
    @PutMapping("/events/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO updatedEventDTO) {
        updatedEventDTO.setId(id);
        return eventService.createEvent(updatedEventDTO);  // Ou une méthode update dédiée si tu préfères
    }

    // Supprimer un événement
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
