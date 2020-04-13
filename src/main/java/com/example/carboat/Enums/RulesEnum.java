package com.example.carboat.Enums;

public enum RulesEnum {


    FIRSTNAME("rule::firstname::length"),
    LASTNAME("rule::lastname::length"),
    EMAIL_ALPHA("rule::email::alpha_rate"),
    EMAIL_NUMBER("rule::email::number_rate"),
    QUOTAION("rule::price::quotation_rate"),
    REGISTER_NUMBER("rule::registernumber::blacklist");

    private String value;

    RulesEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
