package com.andyholes.smsweather.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Component
public class WeatherFetcher {

    @Value("${api.key}")
    private final String API_KEY = "";

    public HashMap<String, Double> fetchWeather(Double lat, Double lon) throws IOException {

        URL url = new URL("https://pro.openweathermap.org/data/2.5/forecast/daily?lat=" + lat + "&" + lon + "={lon}&appid=" + API_KEY + "&cnt=3&units=metric");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        JSONObject jsonObject = new JSONObject(responseStream);
        HashMap<String, Double> weather = new HashMap<>();

        weather.put("day", jsonObject.getDouble("day"));
        weather.put("min", jsonObject.getDouble("min"));
        weather.put("max", jsonObject.getDouble("max"));
        return weather;
    }
}
