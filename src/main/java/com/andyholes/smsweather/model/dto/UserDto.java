package com.andyholes.smsweather.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String id;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @NotBlank
    private String city;

    private boolean active;
}
