package com.example.demo.domain.product.deposit.interest.mapper;

import com.example.demo.domain.product.deposit.interest.dto.InterestDepositProductResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestDepositProductMapper {
    List<InterestDepositProductResponse> getInterest(Long id);
}
