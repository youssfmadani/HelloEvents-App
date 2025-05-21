package com.example.helloevents.services;

import com.example.helloevents.models.Reservation;
import com.example.helloevents.models.User;
import com.example.helloevents.models.Event;
import com.example.helloevents.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation makeReservation(User user, Event event) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setReservationDate(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getReservationsByEvent(Event event) {
        return reservationRepository.findByEvent(event);
    }
}
