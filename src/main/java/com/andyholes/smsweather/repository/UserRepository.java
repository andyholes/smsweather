package com.andyholes.smsweather.repository;

import com.andyholes.smsweather.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findByPhone (String phone);
}
