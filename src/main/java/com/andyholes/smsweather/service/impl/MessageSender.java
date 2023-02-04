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

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@Component
public class MessageSender {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoordinatesFetcher coordinatesFetcher;

    @Autowired
    private WeatherFetcher weatherFetcher;

    @Value("${account.sid}")
    private String accountSid;
    @Value("${service.sid}")
    private String serviceSid;
    @Value("${auth.token}")
    private String authToken;

    String argCode = "+54";
    LocalDate currentDate;

    void sendMessage(String phone, String body) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(argCode + phone),
                        serviceSid,
                        body)
                .create();
        int statusCode;

        if (message.getErrorMessage() != null) {
            throw new NotSentException(message.getErrorMessage());
        }
    }

    @Scheduled(fixedRate = 5000) //usar (cron = "S M H D M A")
    public void sendAllMessages() throws IOException {
        System.out.println("ejecuto");
        for (UserEntity user: userRepository.findAll()) {
            HashMap<String, Double> coordinates = coordinatesFetcher.fetchCoordinates(user.getCity(), user.getCountry());
            HashMap<String, Double> weather = weatherFetcher.fetchWeather(coordinates.get("lat"),coordinates.get("lon"));

            String body =
                    "El clima para la ciudad de " + user.getCity() +
                            " hoy " + currentDate.getDayOfWeek().name() +" "+
                            currentDate.getDayOfMonth() + " de " +
                            currentDate.getMonth().name() +
                            " sera de maxima " + weather.get("max") +
                            " y minima " + weather.get("min") +
                            ". La media sera de " + weather.get("day");
            sendMessage(user.getPhone(),body);
        }
    }
}
