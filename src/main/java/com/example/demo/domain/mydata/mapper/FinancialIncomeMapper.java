package com.example.demo.domain.mydata.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FinancialIncomeMapper {
    @Select("SELECT financialIncomeId FROM FINANCIAL_INCOME WHERE id = #{userId} AND year = #{year}")
    Integer getFinancialIncomeId(String userId, String year);
    @Update("UPDATE FINANCIAL_INCOME SET sum = #{sum}, isOverTax = #{isOverTax} WHERE financialIncomeId = #{financialIncomeId}")
    void updateFinancialIncome(int financialIncomeId, double sum, String isOverTax);
}
