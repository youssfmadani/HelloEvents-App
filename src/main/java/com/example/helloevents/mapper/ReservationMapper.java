package com.example.helloevents.mapper;

import org.mapstruct.Mapper;
import com.example.helloevents.models.Reservation;
import com.example.helloevents.dto.ReservationDTO;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDTO toDto(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
}
