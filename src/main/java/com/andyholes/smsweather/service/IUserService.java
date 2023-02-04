package com.andyholes.smsweather.service;

import com.andyholes.smsweather.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface IUserService {

    UserDto saveUser(UserDto dto) throws IOException;

    UserDto updateUser(String phone, UserDto dto) throws IOException;

    ResponseEntity generateCode(String phone);

    ResponseEntity validateUser(String phone, String code);
}
