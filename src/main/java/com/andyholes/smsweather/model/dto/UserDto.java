package com.andyholes.smsweather.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String id;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @NotBlank
    private String city;

    private String country;

    private boolean active;
}
