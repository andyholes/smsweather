package com.andyholes.smsweather.service;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface IUserService {

    UserDto saveUser(UserDto dto) throws IOException;

    UserDto updateUser(String phone, UserDto dto) throws IOException;

    boolean validateUser(String phone, String code);

    List<UserDto> getAllUsers();

    UserEntity setCoordinates(UserEntity user) throws IOException;
}
