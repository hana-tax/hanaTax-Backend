package com.example.demo.domain.user.entity;

import com.example.demo.global.config.BooleanToYNConverter;
import jakarta.persistence.Convert;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User {
    private int myData;
    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private Date joinDate;
    private String userState;
    private int zipCode;
    private String address;
    private String residentNumber; //주민번호
    @Convert(converter= BooleanToYNConverter.class
    )
    private boolean isAlertTax; //종합과세 알림 신청 여부
    private String userCi;
}
