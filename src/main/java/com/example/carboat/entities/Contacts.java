package com.example.carboat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Contacts {
    private String firstName;
    private String lastName;
    private String email;
    private Phone phone1;
}
