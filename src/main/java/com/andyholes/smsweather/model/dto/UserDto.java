package com.andyholes.smsweather.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String phoneNumber;
    private String address;
}
