package com.example.carboat.Services;

import com.example.carboat.Enums.RulesEnum;
import com.example.carboat.entities.Advert;
import com.example.carboat.entities.ScamResult;
import com.example.carboat.entities.Vehicle;
import com.example.carboat.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class RulesVerificationService {

    public String verification(String filename) throws InterruptedException, JsonProcessingException {

        Advert advert = new Utils().getAdvertFromFile(filename);

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> rules = new ArrayList<>();

        if (firstAndLastNameRule(advert.getContacts().getFirstName()))
            rules.add(RulesEnum.FIRSTNAME.toString());
        if (firstAndLastNameRule(advert.getContacts().getLastName()))
            rules.add(RulesEnum.LASTNAME.toString());
        if (emailAlphaRateRule(advert.getContacts().getEmail()))
            rules.add(RulesEnum.EMAIL_ALPHA.toString());
        if (emailNumberRateRule(advert.getContacts().getEmail()))
            rules.add(RulesEnum.EMAIL_NUMBER.toString());
        if (priceQuotationRate(advert.getVehicle(), advert.getPrice()))
            rules.add(RulesEnum.QUOTAION.toString());
        if (registerNumberBlackListed(advert.getVehicle().getRegisterNumber()))
            rules.add(RulesEnum.REGISTER_NUMBER.toString());

        return objectMapper.writeValueAsString(
                ScamResult.of(
                        advert.getReference(),
                        rules.size() > 0,
                        rules.toArray(new String[0]))
        );
    }

    public Boolean firstAndLastNameRule(String value) {
        if (value != null)
            return value.length() < 3;
        else return true;
    }

    public Boolean emailAlphaRateRule(String email) {
        if (email != null) {
            if (!email.equals("") && email.contains("@")) {
                String emailFirstPart = email.split("@")[0];
                int counter = 0;
                for (Character c : emailFirstPart.toCharArray()) {
                    if (Character.isLetterOrDigit(c)) counter++;
                }
                return !((double) counter / emailFirstPart.length() * 100 > 70);
            } else return true;
        } else return true;
    }

    public Boolean emailNumberRateRule(String email) {
        if (email != null) {
            if (!email.equals("") && email.contains("@")) {
                String emailFirstPart = email.split("@")[0];
                int counter = 0;
                for (Character c : emailFirstPart.toCharArray()) {
                    if (Character.isDigit(c)) counter++;
                }
                return !((double) counter / emailFirstPart.length()  * 100 < 30);
            } else return true;
        } else return true;
    }

    public Boolean priceQuotationRate(Vehicle vehicle, Long price) throws InterruptedException {
        Long quotation = QuotationService.getCoastAdvert(vehicle);
       double res = (double) price / quotation * 100;
        return res < 80 || res > 120;
    }

    public Boolean registerNumberBlackListed(String registerNumber) throws InterruptedException {
        return BlacklistService.isBlacklisted(registerNumber);
    }
}
