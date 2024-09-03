package com.example.demo.domain.account.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositAccount {
    private String depositAccountId;
    private Integer accountPasswd;
    private Integer accountStatus;
    private Double balance;
    private Date createDate;
    private Date expirationDate;
    private Integer depositId;
    private String id;
    private Integer accountType;
}
