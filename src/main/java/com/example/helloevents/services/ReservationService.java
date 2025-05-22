package com.example.helloevents.services;

import com.example.helloevents.dto.EventDTO;
import com.example.helloevents.dto.ReservationDTO;
import com.example.helloevents.dto.UserDTO;
import com.example.helloevents.mapper.EventMapper;
import com.example.helloevents.mapper.ReservationMapper;
import com.example.helloevents.mapper.UserMapper;
import com.example.helloevents.models.Reservation;
import com.example.helloevents.models.User;
import com.example.helloevents.models.Event;
import com.example.helloevents.repositories.EventRepository;
import com.example.helloevents.repositories.ReservationRepository;
import com.example.helloevents.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReservationMapper reservationMapper;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              EventRepository eventRepository,
                              ReservationMapper reservationMapper, EventMapper eventmapper, UserMapper usermapper, UserMapper userMapper, EventMapper eventMapper) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.reservationMapper = reservationMapper;
        this.eventMapper = eventmapper;
        this.userMapper = usermapper;
    }

    public ReservationDTO makeReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(reservationDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setReservationDate(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    public List<ReservationDTO> getReservationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    public List<ReservationDTO> getReservationsByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        List<Reservation> reservations = reservationRepository.findByEvent(event);
        return reservations.stream()
                .map(reservationMapper::toDto)
                .toList();
    }
    public ReservationDTO makeReservation(UserDTO userDTO, EventDTO eventDTO) {
        User user = userMapper.toEntity(userDTO);
        Event event = eventMapper.toEntity(eventDTO);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setReservationDate(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }
    public List<ReservationDTO> getReservationsByUser(UserDTO userDTO) {
        // You need to map UserDTO to User entity
        User user = userMapper.toEntity(userDTO); // Ensure userMapper exists
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsByEvent(EventDTO eventDTO) {
        // You need to map EventDTO to Event entity
        Event event = eventMapper.toEntity(eventDTO); // Ensure eventMapper exists
        List<Reservation> reservations = reservationRepository.findByEvent(event);
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }
}

