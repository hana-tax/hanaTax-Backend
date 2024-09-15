package com.example.demo.domain.mydata.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FinancialIncomeMapper {

    @Select("SELECT financialIncomeId FROM FINANCIAL_INCOME WHERE id = #{userId}")
    Integer getFinancialIncomeId(String userId);

    @Insert("INSERT INTO FINANCIAL_INCOME (financialIncomeId, sum, inquiryDate, isOverTax, id) VALUES (financialIncomeId_seq.NEXTVAL, #{sum}, SYSDATE, #{isOverTax}, #{userId})")
    void insertFinancialIncome(double sum, String isOverTax, String userId);

    @Update("UPDATE FINANCIAL_INCOME SET sum = #{sum}, isOverTax = #{isOverTax} WHERE financialIncomeId = #{financialIncomeId}")
    void updateFinancialIncome(int financialIncomeId, double sum, String isOverTax);
}
