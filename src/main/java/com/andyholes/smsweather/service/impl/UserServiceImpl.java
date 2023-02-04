package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.exception.InvalidCodeException;
import com.andyholes.smsweather.exception.NotFoundException;
import com.andyholes.smsweather.mapper.UserMapper;
import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.repository.UserRepository;
import com.andyholes.smsweather.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private MessageSender messageSender;

    @Override
    public UserDto saveUser(UserDto dto){
        UserEntity user = userMapper.userDto2UserEntity(dto);
        UserEntity savedUser = userRepository.save(user);

        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public ResponseEntity generateCode(String phone) {
        UserEntity user = userRepository.findByPhone(phone);
        if(user == null){
            throw new NotFoundException("The phone number was not found");
        }
        String code = codeGenerator.generateCode();
        user.setCode(code);
        messageSender.sendMessage(phone,"Su codigo de verificacion es: "+code);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDto updateUser(String phone, UserDto dto){
        UserEntity user = userRepository.findByPhone(phone);
        if(user == null){
            throw new NotFoundException("The phone number was not found");
        }
        userMapper.updateUserFromUserDto(dto, user);
        UserEntity savedUser = userRepository.save(user);

        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public ResponseEntity<?> validateUser(String phone, String code) {
        UserEntity user = userRepository.findByPhone(phone);
        if (user.getCode().equals(code)) {
            user.setActive(true);
            user.setCode(codeGenerator.generateCode());
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }else {
            throw new InvalidCodeException("The provided code is not valid");
        }
    }
}
