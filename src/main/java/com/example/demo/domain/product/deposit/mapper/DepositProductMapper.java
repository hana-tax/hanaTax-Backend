package com.example.demo.domain.product.deposit.mapper;

import com.example.demo.domain.product.deposit.dto.DepositProductCreateRequest;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface DepositProductMapper {
    List<DepositProductListResponse> getDepositProductList();
    DepositProductDetailResponse getDepositProductDetail(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "depositId")
    int insertDepositProduct(DepositProductCreateRequest request);
}
