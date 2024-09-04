package com.example.demo.domain.product.isa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio {
    private Long portfolioId; // PORTFOLIOID
    private String portfolioName; // PORTFOLIONAME
    private Double minAccount; // MINACCOUNT
    private Double commission; // COMMISSION
    private byte[] description; // DESCRIPTION (BLOB)
}
