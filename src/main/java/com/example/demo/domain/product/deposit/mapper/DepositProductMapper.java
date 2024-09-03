package com.example.demo.domain.product.deposit.mapper;

import com.example.demo.domain.product.deposit.dto.DepositAccountDto;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepositProductMapper {
    List<DepositProductListResponse> getDepositProductList();
    DepositProductDetailResponse getDepositProductDetail(Long id);
    void signUpForDepositAccount(DepositAccountDto depositAccount);
    void insertJoinHistory(JoinHistoryDto joinHistory);
}
