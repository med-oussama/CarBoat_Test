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
public class Advert {
    private Contacts contacts;
    private Date creationDate;
    private Long price;
    private String[] publicationOptions;
    private String reference;
    private Vehicle vehicle;
}
