package com.example.demo.domain.mydata.mapper;

import com.example.demo.domain.mydata.dto.IncomeDetailDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface IncomeDetailMapper {

    @Insert("INSERT INTO INCOME_DETAIL (incomeId, financialIncomeId, incomeType, accountNumber, institutionName, incomeAccount, incomeTax, localTax, incomeDate) VALUES (INCOMEDETAILID_SEQ.nextval, #{financialIncomeId}, #{incomeType}, #{accountNumber}, #{institutionName}, #{incomeAccount}, #{incomeTax}, #{localTax}, #{incomeDate})")
    void insertIncomeDetail(IncomeDetailDto incomeDetailDto);
}
