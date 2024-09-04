package com.example.demo.domain.product.isa.service;

import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import com.example.demo.domain.product.isa.mapper.IsaProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IsaProductServiceImpl implements  IsaProductService{
    private final IsaProductMapper isaProductMapper;

    @Override
    public List<IsaPortfolioListResponse> getPortfolio() {
        return isaProductMapper.getPortfolio();
    }
}
