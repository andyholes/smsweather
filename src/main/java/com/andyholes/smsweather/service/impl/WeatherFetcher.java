package com.andyholes.smsweather.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
@PropertySource("application.properties")
public class WeatherFetcher {

    @Value("${api.key}")
    private String apiKey;

    public HashMap<String, Double> fetchWeather(Double lat, Double lon){

        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&cnt=3&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        String data = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(data);
        HashMap<String, Double> weather = new HashMap<>();

        JSONObject details = jsonObject
                .getJSONArray("list").getJSONObject(0)
                .getJSONObject("main");
        weather.put("feels_like", details.getDouble("feels_like"));
        weather.put("temp_max", details.getDouble("temp_max"));
        weather.put("temp_min", details.getDouble("temp_min"));

        return weather;
    }
}
