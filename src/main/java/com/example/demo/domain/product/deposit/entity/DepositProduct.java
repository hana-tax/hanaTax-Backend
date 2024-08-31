package com.example.demo.domain.product.deposit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositProduct {
    private Long depositId;

    private Integer depositType;

    @Column(length = 100)
    private String depositName;

    @Column(precision = 5, scale = 2)
    private BigDecimal minInterestRate;

    @Column(precision = 5, scale = 2)
    private BigDecimal maxInterestRate;

    private BigDecimal minJoinAmount;

    private BigDecimal maxJoinAmount;

    private Integer minPeriod;

    private Integer maxPeriod;

    @Column(length = 255)
    private String features;

    @Column(length = 255)
    private String notice;

    @Lob
    private byte[] terms;

    @Lob
    private byte[] description;

    @Column(length = 255)
    private String target;

    private Integer depositSort;

    @Column(length = 255)
    private String taxBenefits;

    private Integer savingType;
}
