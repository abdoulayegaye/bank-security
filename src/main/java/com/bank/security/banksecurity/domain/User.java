package com.bank.security.banksecurity.domain;

import com.bank.security.banksecurity.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    Long id;
    @NotNull(message = "Firstname is not null")
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Gender gender;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String address;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
