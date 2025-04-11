package com.example.marketplace.mapper;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
