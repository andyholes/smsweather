package com.andyholes.smsweather.service.impl;

import com.andyholes.smsweather.exception.NotSentException;
import com.andyholes.smsweather.model.UserEntity;
import com.andyholes.smsweather.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private UserRepository userRepository;

    @Value("${account.sid}")
    private final String ACCOUNT_SID = "";
    @Value("${service.sid}")
    private final String SERVICE_SID = "";
    @Value("${auth.token}")
    private final String AUTH_TOKEN = "";

    String argCode = "+54";

    public MessageSender() {
    }

    void sendMessage(String phone, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(argCode + phone),
                        SERVICE_SID,
                        body)
                .create();
        int statusCode;

        if (message.getErrorMessage() != null) {
            throw new NotSentException(message.getErrorMessage());
        }
    }

    @Scheduled
    public void sendAllMessages(){
        for (UserEntity user: userRepository.findAll()) {
            String body =
                    //TODO: armar body con datos obtenidos
                    "El clima en ciudad de " +
                    user.getCity() + "sera de 20 a 30 grados"
                    ;
            sendMessage(user.getPhone(),body);
        }
    }
}
