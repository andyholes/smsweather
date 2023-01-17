package com.andyholes.smsweather.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String phoneNumber;
    private boolean isActive;
    private String address;
    private Long latitude;
    private Long longitude;
    private String verificationCode;
}
