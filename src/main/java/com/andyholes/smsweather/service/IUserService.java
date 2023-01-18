package com.andyholes.smsweather.service;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    UserDto saveUser(UserDto dto);

    UserDto updateUser(UserDto dto);

    boolean validateUser (String phone, String code);

    boolean deactivateUser (String phone, String code);

    List<UserDto> getAllUsers();
    
    String generateCode();
}
