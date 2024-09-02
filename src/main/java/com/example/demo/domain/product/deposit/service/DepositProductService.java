package com.example.demo.domain.product.deposit.service;

import com.example.demo.domain.product.deposit.dto.DepositProductCreateRequest;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepositProductService {
    List<DepositProductListResponse> getFinancialProductList();
    DepositProductDetailResponse getDepositProductDetail(Long id);
    boolean addDepositProduct(DepositProductCreateRequest request, MultipartFile termsFile, MultipartFile descriptionFile);
}
