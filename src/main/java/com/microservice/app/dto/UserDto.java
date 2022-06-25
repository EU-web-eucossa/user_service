package com.microservice.app.dto;

import com.sun.istack.NotNull;

import javax.persistence.Id;

public class UserDto {
    @Id
    @NotNull
    private String email;
    @NotNull
    private String userName;
    @NotNull
    private String city;
    @NotNull
    private Integer zip;
    @NotNull
    private String phoneNumber;
}
