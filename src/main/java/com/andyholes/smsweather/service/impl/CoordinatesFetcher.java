package com.andyholes.smsweather.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

@Component
public class CoordinatesFetcher {

    @Value("${api.key}")
    private String apiKey;

    public HashMap<String, Double> fetchCoordinates(String city, String country) throws IOException {

        URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + URLEncoder.encode(city) + "," + country + "&limit=1&appid=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        JSONObject jsonObject = new JSONObject(responseStream);
        HashMap<String, Double> coordinates = new HashMap<>();

        coordinates.put("lat", jsonObject.getDouble("lat"));
        coordinates.put("lon", jsonObject.getDouble("lon"));
        return coordinates;
    }
}
