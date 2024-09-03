package com.example.demo.domain.product.deposit.service;

import com.example.demo.domain.product.deposit.dto.DepositAccountDto;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepositProductService {
    List<DepositProductListResponse> getFinancialProductList();
    DepositProductDetailResponse getDepositProductDetail(Long id);
    void createDepositAccount(DepositAccountDto depositAccount, JoinHistoryDto joinHistory);
}
