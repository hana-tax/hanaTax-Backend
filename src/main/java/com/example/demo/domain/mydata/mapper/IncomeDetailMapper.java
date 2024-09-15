package com.example.demo.domain.mydata.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface IncomeDetailMapper {

    @Insert("INSERT INTO INCOME_DETAIL (incomeId, financialIncomeId, incomeType, accountNumber, institutionName, incomeAccount, incomeTax, localTax, incomeDate) VALUES (#{incomeId}, #{financialIncomeId}, #{incomeType}, #{accountNumber}, #{institutionName}, #{incomeAccount}, #{incomeTax}, #{localTax}, #{incomeDate})")
    void insertIncomeDetail(int incomeId, int financialIncomeId, int incomeType, String accountNumber, int institutionName, double incomeAccount, double incomeTax, double localTax, String incomeDate);
}
