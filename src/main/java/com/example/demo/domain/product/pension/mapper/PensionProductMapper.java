package com.example.demo.domain.product.pension.mapper;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.isa.dto.IsaAccountDto;
import com.example.demo.domain.product.pension.dto.PensionAccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PensionProductMapper {
    void signUpForPensionAccount(PensionAccountDto pensionAccount);
    void insertJoinHistory(JoinHistoryDto joinHistory);
}
