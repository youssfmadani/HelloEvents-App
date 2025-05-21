package com.example.helloevents.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "events")
public class Event {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private String category;
    private LocalDateTime date;
    private String description;
    private int availableSeats;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}