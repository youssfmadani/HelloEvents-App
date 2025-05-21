package com.example.helloevents.controllers;


import com.example.helloevents.models.Event;
import com.example.helloevents.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getHomeEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/about")
    public String getAboutInfo() {
        return "Notre équipe est passionnée par l'organisation d'événements. Nous valorisons la qualité, la diversité, et la satisfaction client.";
    }
}
