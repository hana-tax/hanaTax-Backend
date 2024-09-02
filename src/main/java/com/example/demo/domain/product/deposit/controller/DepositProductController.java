package com.example.demo.domain.product.deposit.controller;

import com.example.demo.domain.product.deposit.dto.DepositProductCreateRequest;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.service.DepositProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Tag(name="DepositProduct")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product/deposit")
public class DepositProductController {
    private final DepositProductService depositProductService;

    @GetMapping("/list")
    public ResponseEntity<List<DepositProductListResponse>> getFinancialProductList() {
        return ResponseEntity.ok(depositProductService.getFinancialProductList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositProductDetailResponse> getDepositProductDetail(@PathVariable Long id) {
        DepositProductDetailResponse productDetail = depositProductService.getDepositProductDetail(id);
        if (productDetail != null) {
            return ResponseEntity.ok(productDetail);
        } else {
            return ResponseEntity.notFound().build(); // 상품이 없을 경우 404 반환
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Void> addDepositProduct(
            @RequestParam("termsFile") MultipartFile termsFile,
            @RequestParam("descriptionFile") MultipartFile descriptionFile,
            @ModelAttribute DepositProductCreateRequest request) {

        boolean isCreated = depositProductService.addDepositProduct(request, termsFile, descriptionFile);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
    }
}
