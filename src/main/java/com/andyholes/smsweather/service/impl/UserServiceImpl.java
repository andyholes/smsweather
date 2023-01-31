package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.exception.NotFoundException;
import com.andyholes.smsweather.mapper.UserMapper;
import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.repository.UserRepository;
import com.andyholes.smsweather.service.IUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final String COUNTRY_CODE = "AR";
    private final String GEOCODING_API_KEY = "";

    @Override
    public UserDto saveUser(UserDto dto) throws IOException {
        UserEntity user = userMapper.userDto2UserEntity(dto);
        setCoordinates(user);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userEntity2UserDto(savedUser);
    }

    @Override
    public UserDto updateUser(String phone, UserDto dto) throws IOException {
        UserEntity user = userRepository.findByPhone(phone);
        if(user == null){
            throw new NotFoundException("The phone number is not registered");
        }
        userMapper.updateUserFromUserDto(dto, user);
        if (!dto.getCity().equals(user.getCity())){
            setCoordinates(user);
        }
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

    public UserEntity setCoordinates(UserEntity user) throws IOException {

        URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + user.getCity() + "," + COUNTRY_CODE + "&limit=1&appid=" + GEOCODING_API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        JSONObject jsonObject = new JSONObject(responseStream);
        Double lat = jsonObject.getDouble("lat");
        Double lon = jsonObject.getDouble("lon");

        user.setLatitude(lat);
        user.setLongitude(lon);

        return user;
    }
}
