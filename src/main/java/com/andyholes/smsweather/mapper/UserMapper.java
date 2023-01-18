package com.andyholes.smsweather.mapper;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userEntity2UserDto(UserEntity user);

    UserEntity userDto2UserEntity(UserDto dto);

    UserEntity updateUser(UserDto dto);

    List<UserDto> toDtoList(List<UserEntity> users);
}
