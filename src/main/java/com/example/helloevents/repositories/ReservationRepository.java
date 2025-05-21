package com.example.helloevents.repositories;


import com.example.helloevents.models.Reservation;
import com.example.helloevents.models.User;
import com.example.helloevents.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByEvent(Event event);
}
