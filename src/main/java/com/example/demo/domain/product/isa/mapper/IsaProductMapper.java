package com.example.demo.domain.product.isa.mapper;

import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IsaProductMapper {
    List<IsaPortfolioListResponse> getPortfolio();
}
