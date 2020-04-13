package com.example.carboat.Services;

import com.example.carboat.entities.Vehicle;

public class QuotationService {

    private static final Long COAST = 35000L;

    public static Long getCoastAdvert(Vehicle vehicle) throws InterruptedException {
        Thread.sleep(50);
        return COAST;
    }
}
