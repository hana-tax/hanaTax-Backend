package com.example.demo.domain.income.mapper;

import com.example.demo.domain.income.dto.IncomeIsOverTaxResponse;
import com.example.demo.domain.income.dto.IncomeListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IncomeMapper {
    @Select("SELECT sum,isOverTax,financialIncomeId " +
            "FROM financial_income " +
            "WHERE ID = #{id} ")
    IncomeIsOverTaxResponse getIncomeListById(String id);

    @Select("SELECT incomeType,accountNumber,institutionName,incomeAccount,incomeTax,localTax,incomeDate " +
            "FROM income_detail " +
            "WHERE financialIncomeId = #{financialIncomeId} ")
    List<IncomeListResponse> getIncomeAllListById(int financialIncomeId);
}
