package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.mapper.UserMapper;
import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.repository.UserRepository;
import com.andyholes.smsweather.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto dto) {
        UserEntity user = userMapper.userDto2UserEntity(dto);
        user.setCode(generateCode());
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        return null;
    }

    @Override
    public boolean validateUser(String phone, String code) {
        return false;
    }

    @Override
    public boolean deactivateUser(String phone, String code) {
        return false;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public String generateCode() {
        Random rand = new Random();
        String code = rand.ints(48, 123)
                .filter(num -> (num<58 || num>64) && (num<91 || num>96))
                .limit(6)
                .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
        return code;
    }
}
