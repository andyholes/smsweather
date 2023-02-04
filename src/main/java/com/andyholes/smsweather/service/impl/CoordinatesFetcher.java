package com.andyholes.smsweather.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

@Component
@PropertySource("application.properties")
public class CoordinatesFetcher {

    @Value("${api.key}")
    private String apiKey;

    public HashMap<String, Double> fetchCoordinates(String city, String country) throws IOException {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + URLEncoder.encode(city,"UTF-8") + "," + country + "&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        String data = restTemplate.getForObject(url, String.class);
        StringBuilder dataJsonFormat = new StringBuilder(data)
                .deleteCharAt(data.length()-1)
                .deleteCharAt(0);
        JSONObject jsonObject = new JSONObject(dataJsonFormat.toString());
        HashMap<String, Double> coordinates = new HashMap<>();

        coordinates.put("lat", jsonObject.getDouble("lat"));
        coordinates.put("lon", jsonObject.getDouble("lon"));
        return coordinates;
    }
}
