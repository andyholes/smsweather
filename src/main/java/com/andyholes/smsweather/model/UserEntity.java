package com.andyholes.smsweather.model;

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
    private Long id;
    private String phoneNumber;
    private boolean isActive = false;
    private String address;
    private Long latitude;
    private Long longitude;
    private String code;
}