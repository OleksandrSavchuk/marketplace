package com.example.marketplace.mapper;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.User;
import com.example.marketplace.entity.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {

    @Mapping(target = "role", expression = "java(mapRole(userDto.getRole()))")
    User toEntity(UserDto userDto);

    default Role mapRole(String roleStr) {
        return Role.valueOf("ROLE_" + roleStr.toUpperCase());
    }

}
