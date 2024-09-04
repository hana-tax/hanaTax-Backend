package com.example.demo.domain.product.isa.dto;

import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IsaPortfolioListResponse {
    private Long portfolioId; // PORTFOLIOID
    private String portfolioName; // PORTFOLIONAME
    private Double minAccount; // MINACCOUNT
    private Double commission; // COMMISSION
    private byte[] description; // DESCRIPTION (BLOB)

    public static IsaPortfolioListResponse of(
                   Long portfolioId,
                   String portfolioName,
                   Double minAccount,
                   Double commission,
                   byte[] description) {

        return IsaPortfolioListResponse.builder()
                .portfolioId(portfolioId)
                .portfolioName(portfolioName)
                .minAccount(minAccount)
                .commission(commission)
                .description(description)
                .build();
    }
}
