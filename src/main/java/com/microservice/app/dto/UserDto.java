package com.microservice.app.dto;

import com.sun.istack.NotNull;

import javax.persistence.Id;

public class UserDto {
    @Id
    @NotNull
    private String email;

    private String lastName;
    private Double credit;
}
