package com.example.demo.domain.product.deposit.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositProductDetailResponse {
    private Long depositId;

    private Integer depositType;

    private String depositName;

    private BigDecimal minInterestRate;

    private BigDecimal maxInterestRate;

    private BigDecimal minJoinAmount;

    private BigDecimal maxJoinAmount;

    private Integer minPeriod;

    private Integer maxPeriod;

    private String features;


    private String notice;

    @Lob
    private byte[] terms;

    @Lob
    private byte[] description;

    private String target;

    private Integer depositSort;

    private String taxBenefits;

    private Integer savingType;


    public static DepositProductDetailResponse of(
            Long depositId,
            Integer depositType,
            String depositName,
            BigDecimal minInterestRate,
            BigDecimal maxInterestRate,
            BigDecimal minJoinAmount,
            BigDecimal maxJoinAmount,
            Integer minPeriod,
            Integer maxPeriod,
            String features,
            String notice,
            byte[] terms,
            byte[] description,
            String target,
            Integer depositSort,
            String taxBenefits,
            Integer savingType) {

        return DepositProductDetailResponse.builder()
                .depositId(depositId)
                .depositType(depositType)
                .depositName(depositName)
                .minInterestRate(minInterestRate)
                .maxInterestRate(maxInterestRate)
                .minJoinAmount(minJoinAmount)
                .maxJoinAmount(maxJoinAmount)
                .minPeriod(minPeriod)
                .maxPeriod(maxPeriod)
                .features(features)
                .notice(notice)
                .terms(terms)
                .description(description)
                .target(target)
                .depositSort(depositSort)
                .taxBenefits(taxBenefits)
                .savingType(savingType)
                .build();
    }
}
