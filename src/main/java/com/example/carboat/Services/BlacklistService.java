package com.example.carboat.Services;

public class BlacklistService {

    private static final String REGISTRATION = "AA123AA";

    public static Boolean isBlacklisted(String registration) throws InterruptedException {
        Thread.sleep(50);
        return !registration.equals("") && !registration.equals(REGISTRATION);
    }
}
