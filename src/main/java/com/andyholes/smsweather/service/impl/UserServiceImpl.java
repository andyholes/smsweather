package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.repository.UserRepository;
import com.andyholes.smsweather.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository user;

    @Override
    public UserDto saveUser(UserEntity user) {
        return null;
    }

    @Override
    public UserDto updateUser(UserEntity user) {
        return null;
    }

    @Override
    public boolean validateUser(String code) {
        return false;
    }

    @Override
    public boolean deactivateUser(String phone) {
        return false;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }
}
