package com.example.demo.domain.product.deposit.interest.service;

import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.interest.dto.InterestDepositProductResponse;
import com.example.demo.domain.product.deposit.interest.mapper.InterestDepositProductMapper;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestDepositProductServiceImpl implements InterestDepositProductService{
    private final InterestDepositProductMapper interestDepositProductMapper;

    @Override
    public List<InterestDepositProductResponse> getInterest(Long id) {
        return interestDepositProductMapper.getInterest(id);
    }
}
