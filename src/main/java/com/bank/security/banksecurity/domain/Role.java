package com.bank.security.banksecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;
    @NotNull
    private String name;
}
