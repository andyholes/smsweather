package com.andyholes.smsweather.mapper;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "isActive", ignore = true)
    UserDto userEntity2UserDto(UserEntity user);

    UserEntity userDto2UserEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity updateUserFromUserDto(UserDto dto, @MappingTarget UserEntity user);
    List<UserDto> toDtoList(List<UserEntity> users);
}
