package com.example.carboat;

import com.example.carboat.Services.RulesVerificationService;
import com.example.carboat.entities.Vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MainAppTests {



    private RulesVerificationService rulesVerificationService = new RulesVerificationService();

    @Test
    void verification_Test_when_data_OK() throws JsonProcessingException, InterruptedException {
        String res = rulesVerificationService.verification("Advert.json");
        Assertions.assertEquals(res,"{\"reference\":\"B300053623\",\"scam\":false,\"rules\":[]}");
    }

    @Test
    void verification_Test_when_data_NOK() throws JsonProcessingException, InterruptedException {
        String res = rulesVerificationService.verification("AdvertForTestKO.json");
        Assertions.assertEquals(res,"{\"reference\":\"B300053623\",\"scam\":true,\"rules\":[\"rule::firstname::length\",\"rule::lastname::length\",\"rule::email::number_rate\",\"rule::price::quotation_rate\",\"rule::registernumber::blacklist\"]}");
    }

    @Test
    void firstAndLastNameRule_when_data_NOK_then_true() {
        Boolean res = rulesVerificationService.firstAndLastNameRule("ab");
        Assertions.assertEquals(res,true);
    }


    @Test
    void firstAndLastNameRule_Test_when_data_OK_then_false() {
        Boolean res = rulesVerificationService.firstAndLastNameRule("abcde");
        Assertions.assertEquals(res,false);
    }

    @Test
    void emailAlphaRateRule_Test_when_data_OK_then_false() {
        Boolean res = rulesVerificationService.emailAlphaRateRule("med_91@gmail.com");
        Assertions.assertEquals(res,false);
    }


    @Test
    void emailAlphaRateRule_Test_when_data_NOK_then_true() {
        Boolean res = rulesVerificationService.emailAlphaRateRule("m_e_d_9_1_@gmail.com");
        Assertions.assertEquals(res,true);
    }

    @Test
    void emailNumberRateRule_Test_when_data_NOK_then_true() {
        Boolean res = rulesVerificationService.emailNumberRateRule("med_199111@gmail.com");
        Assertions.assertEquals(res,true);
    }

    @Test
    void emailNumberRateRule_Test_when_data_OK_then_false() {
        Boolean res = rulesVerificationService.emailNumberRateRule("med_ouss91@gmail.com");
        Assertions.assertEquals(res,false);
    }

    @Test
    void priceQuotationRate_Test_when_data_OK_then_false_1() throws InterruptedException {
        Boolean res = rulesVerificationService.priceQuotationRate(new Vehicle(), 32000L);
        Assertions.assertEquals(res,false);
    }

    @Test
    void priceQuotationRate_Test_when_data_OK_then_false_2() throws InterruptedException {
        Boolean res = rulesVerificationService.priceQuotationRate(new Vehicle(), 38000L);
        Assertions.assertEquals(res,false);
    }

    @Test
    void priceQuotationRate_Test_when_data_NOK_then_true_1() throws InterruptedException {
        Boolean res = rulesVerificationService.priceQuotationRate(new Vehicle(), 19000L);
        Assertions.assertEquals(res,true);
    }

    @Test
    void priceQuotationRate_Test_when_data_NOK_then_true_2() throws InterruptedException {
        Boolean res = rulesVerificationService.priceQuotationRate(new Vehicle(), 50000L);
        Assertions.assertEquals(res,true);
    }

    @Test
    void registerNumberBlackListed_Test_when_data_NOK_then_true() throws InterruptedException {
        Boolean res = rulesVerificationService.registerNumberBlackListed("AAADD333");
        Assertions.assertEquals(res,true);
    }

    @Test
    void registerNumberBlackListed_Test_when_data_OK_then_false() throws InterruptedException {
        Boolean res = rulesVerificationService.registerNumberBlackListed("AA123AA");
        Assertions.assertEquals(res,false);
    }

}
