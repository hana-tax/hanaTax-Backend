package com.example.demo.domain.product.deposit.mapper;

import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepositProductMapper {
    List<DepositProductListResponse> getDepositProductList();
    DepositProductDetailResponse getDepositProductDetail(Long id);
}
