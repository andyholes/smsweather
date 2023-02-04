package com.andyholes.smsweather.rest;

import com.andyholes.smsweather.model.dto.UserDto;
import com.andyholes.smsweather.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userSevice;

    @PostMapping("/user")
    private ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto dto) throws IOException {
        UserDto savedUser = userSevice.saveUser(dto);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/code")
    ResponseEntity<?> generateCode(@RequestAttribute("phone") String phone){
        return userSevice.generateCode(phone);
    }

    @PostMapping("/code")
    ResponseEntity<?> validateCode(@RequestAttribute("phone") String phone,
                                @RequestAttribute("code") String code){
        return userSevice.validateUser(phone, code);
    }
}
