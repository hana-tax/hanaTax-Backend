package com.example.demo.domain.product.deposit.interest.service;

import com.example.demo.domain.product.deposit.interest.dto.InterestDepositProductResponse;

import java.util.List;

public interface InterestDepositProductService {
    List<InterestDepositProductResponse> getInterest(Long id);
}
