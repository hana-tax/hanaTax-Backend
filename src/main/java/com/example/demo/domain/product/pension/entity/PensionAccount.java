package com.example.demo.domain.product.pension.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PensionAccount {
    private String fundAccountNo; // FUNDACCOUNTNO
    private Integer accountPasswd; // ACCOUNTPASSWD
    private Date createDate; // CREATEDATE
    private Date expirationDate; // EXPIRATIONDATE
    private Integer accountStatus; // ACCOUNTSTATUS
    private String sum; // SUM
    private String balance; // BALANCE
    private Integer period; // PERIOD
    private Integer paymentLimit; // PAYMENTLIMIT
    private String connectAccountId; // CONNECTACCOUNTID
    private String id; // ID
    private Integer accountType; // ACCOUNTTYPE
    private Integer pensionAge; // PENSIONAGE
    private Integer pensionCycle; // PENSIONCYCLE
}
