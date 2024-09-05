package com.example.demo.domain.product.pension.service;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.pension.dto.PensionAccountDto;

public interface PensionProductService {
    void createPensionAccount(PensionAccountDto pensionAccountDto, JoinHistoryDto joinHistory);
}
