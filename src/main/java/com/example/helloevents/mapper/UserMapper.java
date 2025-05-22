package com.example.helloevents.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.helloevents.dto.UserDTO;
import com.example.helloevents.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDto);
}