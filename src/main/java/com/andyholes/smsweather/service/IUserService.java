package com.andyholes.smsweather.service;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    UserDto saveUser(UserEntity user);

    UserDto updateUser(UserEntity user);

    boolean validateUser (String code);

    boolean deactivateUser (String phone);

    List<UserDto> getAllUsers();
}
