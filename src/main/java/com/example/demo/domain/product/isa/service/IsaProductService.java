package com.example.demo.domain.product.isa.service;

import com.example.demo.domain.product.deposit.dto.DepositAccountDto;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.isa.dto.IsaAccountDto;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;

import java.util.List;

public interface IsaProductService {
    List<IsaPortfolioListResponse> getPortfolio();
    void createIsaAccount(IsaAccountDto isaAccountDto, JoinHistoryDto joinHistory);
}
