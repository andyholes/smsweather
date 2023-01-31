package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.exception.NotFoundException;
import com.andyholes.smsweather.mapper.UserMapper;
import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.repository.UserRepository;
import com.andyholes.smsweather.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto dto){
        UserEntity user = userMapper.userDto2UserEntity(dto);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public UserDto updateUser(String phone, UserDto dto){
        UserEntity user = userRepository.findByPhone(phone);
        if(user == null){
            throw new NotFoundException("The phone number is not registered");
        }
        userMapper.updateUserFromUserDto(dto, user);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public boolean validateUser(String phone, String code) {
        UserEntity user = userRepository.findByPhone(phone);
        return (user.getCode().equals(code));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }
}
