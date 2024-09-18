package com.example.demo.domain.mydata.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class FinancialIncomeDto {
    private int financialIncomeId;
    private double sum;
    private Date inquiryDate;
    private String isOverTax;
    private String id;
    private String year;
}
