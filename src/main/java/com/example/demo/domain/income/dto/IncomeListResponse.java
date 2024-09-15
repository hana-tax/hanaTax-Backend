package com.example.demo.domain.income.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeListResponse {
   private int incomeType;
   private String accountNumber;
   private int institutionName;
   private int incomeAccount;
   private int incomeTax;
   private int localTax;
   private Date incomeDate;
}
