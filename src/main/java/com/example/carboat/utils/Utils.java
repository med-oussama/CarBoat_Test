package com.example.carboat.utils;

import com.example.carboat.entities.Advert;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {
    public Advert getAdvertFromFile(String filename) {

        File file = null;
        Advert advert = new Advert();
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/"+filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines().collect(Collectors.joining(""));

            advert = objectMapper.readValue(contents, Advert.class);
        } catch (IOException e) {
            e.getMessage();
        }
        return advert;
    }
}
