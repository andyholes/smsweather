package com.andyholes.smsweather.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @NotBlank
    private String address;

    private String latitude;

    private String longitude;

    private boolean isActive;

    private String code;
}
