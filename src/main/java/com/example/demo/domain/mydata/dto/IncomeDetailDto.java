package com.example.demo.domain.mydata.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class IncomeDetailDto {
    private Integer financialIncomeId;
    private Integer incomeType;
    private String accountNumber;
    private Integer institutionName;
    private double incomeAccount;
    private double incomeTax;
    private double localTax;
    private String incomeDate;
}
