package com.andyholes.smsweather.repository;

import com.andyholes.smsweather.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findByPhone (String phone);

    @Query("{'active':true}")
    List<UserEntity> findAll();
}
