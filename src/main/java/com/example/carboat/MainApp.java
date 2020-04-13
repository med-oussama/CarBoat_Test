package com.example.carboat;

import com.example.carboat.Services.RulesVerificationService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MainApp {
    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        System.out.println(new RulesVerificationService().verification("Advert.json"));
    }
}
