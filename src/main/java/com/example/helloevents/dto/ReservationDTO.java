package com.example.helloevents.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDateTime reservationDate;
    private int numberOfTickets;

    public ReservationDTO() {}

    public ReservationDTO(Long id, Long userId, Long eventId, LocalDateTime reservationDate, int numberOfTickets) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.reservationDate = reservationDate;
        this.numberOfTickets = numberOfTickets;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }

    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}
