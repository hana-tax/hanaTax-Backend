package com.example.demo.domain.product.deposit.service;

import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositProductServiceImpl implements DepositProductService{

    private final DepositProductMapper depositProductMapper;
    @Override
    public List<DepositProductListResponse> getFinancialProductList() {
        return depositProductMapper.getDepositProductList();
    }
    @Override
    public DepositProductDetailResponse getDepositProductDetail(Long id) {
        return depositProductMapper.getDepositProductDetail(id);
    }
}
