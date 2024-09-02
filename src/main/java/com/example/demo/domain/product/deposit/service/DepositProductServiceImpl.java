package com.example.demo.domain.product.deposit.service;

import com.example.demo.domain.product.deposit.dto.DepositProductCreateRequest;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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

    @Override
    public boolean addDepositProduct(DepositProductCreateRequest request, MultipartFile termsFile, MultipartFile descriptionFile) {
        try {
            System.out.println("termsFile Size: " + termsFile.getSize());
            System.out.println("descriptionFile Size: " + descriptionFile.getSize());

            // DTO에 파일의 바이트 배열 설정
            request.setTerms(termsFile.getBytes());
            request.setDescription(descriptionFile.getBytes());



            // 데이터베이스에 저장
            int rowsInserted = depositProductMapper.insertDepositProduct(request);
            System.out.println("row"+rowsInserted);
            return rowsInserted > 0;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("123");
            return false;
        }
    }
}
