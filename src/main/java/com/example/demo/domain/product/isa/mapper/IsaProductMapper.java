package com.example.demo.domain.product.isa.mapper;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.isa.dto.IsaAccountDto;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IsaProductMapper {
    List<IsaPortfolioListResponse> getPortfolio();
    void signUpForIsaAccount(IsaAccountDto isaAccountDto);
    void insertJoinHistory(JoinHistoryDto joinHistory);
}
