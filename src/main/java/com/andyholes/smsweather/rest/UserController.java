package com.andyholes.smsweather.rest;

import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userSevice;

    @PostMapping
    private ResponseEntity<UserDto> saveUser(@RequestBody UserDto dto){
        UserDto savedUser = userSevice.saveUser(dto);
        return ResponseEntity.ok(dto);
    }
}
