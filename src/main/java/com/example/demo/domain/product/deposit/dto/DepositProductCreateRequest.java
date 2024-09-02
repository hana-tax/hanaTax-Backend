package com.example.demo.domain.product.deposit.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositProductCreateRequest {
    private Long depositId; // DEPOSITID
    private Integer depositType; // DEPOSITTYPE
    private String depositName; // DEPOSITNAME
    private BigDecimal minInterestRate; // MININTERESTRATE
    private BigDecimal maxInterestRate; // MAXINTERESTRATE
    private BigDecimal minJoinAmount; // MINJOINAMOUNT
    private BigDecimal maxJoinAmount; // MAXJOINAMOUNT
    private Integer minPeriod; // MINPERIOD
    private Integer maxPeriod; // MAXPERIOD
    private String features; // FEATURES
    private String notice; // NOTICE
    private byte[] terms; // TERMS (BLOB)
    private byte[] description; // DESCRIPTION (BLOB)
    private String target; // TARGET
    private Integer depositSort; // DEPOSITSORT
    private String taxBenefits; // TAXBENEFITS
    private Integer savingType; // SAVINGTYPEpackage com.example.demo.domain.product.deposit.dto;

}
