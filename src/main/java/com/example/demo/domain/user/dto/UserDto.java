package com.example.demo.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Date joinDate;
    private String userState;
    private int zipCode;
    private String address;
    private String residentNumber;
    private String userCi;
    private int myDataId;
    private char alertMethodSMS;
    private char alertMethodEmail;
}
