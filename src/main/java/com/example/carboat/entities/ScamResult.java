package com.example.carboat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ScamResult {
    private String reference;
    private Boolean scam;
    private String[] rules;
}
