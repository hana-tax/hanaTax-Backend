package com.example.demo.domain.mydata.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class IncomeDetailDto {
    private int incomeId;
    private int financialIncomeId;
    private int incomeType;
    private String accountNumber;
    private int institutionName;
    private double incomeAccount;
    private double incomeTax;
    private double localTax;
    private String incomeDate;
}
