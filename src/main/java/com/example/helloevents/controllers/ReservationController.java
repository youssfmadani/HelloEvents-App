package com.example.helloevents.controllers;

import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.dto.ReservationDTO;
import com.example.helloevents.dto.UserDTO;
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
    public ResponseEntity<ReservationDTO> reserve(@RequestParam Long userId, @RequestParam Long eventId) {
        Optional<UserDTO> userOpt = userService.getUserById(userId);
        Optional<EventDTO> eventOpt = eventService.getEventById(eventId);

        if (userOpt.isPresent() && eventOpt.isPresent()) {
            ReservationDTO reservation = reservationService.makeReservation(userOpt.get(), eventOpt.get());
            return ResponseEntity.ok(reservation);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getUserReservations(@PathVariable long userId) {
        Optional<UserDTO> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            List<ReservationDTO> reservations = reservationService.getReservationsByUser(userOpt.get().getId());
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ReservationDTO>> getEventReservations(@PathVariable Long eventId) {
        Optional<EventDTO> eventOpt = eventService.getEventById(eventId);
        if (eventOpt.isPresent()) {
            List<ReservationDTO> reservations = reservationService.getReservationsByEvent(eventOpt.get().getId());
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.notFound().build();
    }
}