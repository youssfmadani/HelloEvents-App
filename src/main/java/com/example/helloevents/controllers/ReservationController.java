package com.example.helloevents.controllers;

import com.example.helloevents.models.Event;
import com.example.helloevents.models.Reservation;
import com.example.helloevents.models.User;
import com.example.helloevents.services.EventService;
import com.example.helloevents.services.ReservationService;
import com.example.helloevents.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Reservation> reserve(@RequestParam Long userId, @RequestParam Long eventId) {
        Optional<User> userOpt = userService.getUserById(userId);
        Optional<Event> eventOpt = eventService.getEventById(eventId);

        if (userOpt.isPresent() && eventOpt.isPresent()) {
            Reservation reservation = reservationService.makeReservation(userOpt.get(), eventOpt.get());
            return ResponseEntity.ok(reservation);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getUserReservations(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(reservationService::getReservationsByUser)
                .orElse(List.of());
    }

    @GetMapping("/event/{eventId}")
    public List<Reservation> getEventReservations(@PathVariable Long eventId) {
        return eventService.getEventById(eventId)
                .map(reservationService::getReservationsByEvent)
                .orElse(List.of());
    }
}
