package com.example.demo.domain.product.deposit.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestDepositProductResponse {
    private Long depositId;
    private Long interestId;
    private Integer duration;
    private Double interestRate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public static InterestDepositProductResponse of(
            Long depositId,
            Long interestId,
            Integer duration,
            Double interestRate,
            Timestamp createdAt,
            Timestamp updatedAt) {

        return InterestDepositProductResponse.builder()
                .depositId(depositId)
                .interestId(interestId)
                .duration(duration)
                .interestRate(interestRate)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
