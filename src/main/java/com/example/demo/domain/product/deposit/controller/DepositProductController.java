package com.example.demo.domain.product.deposit.controller;

import com.example.demo.domain.product.deposit.dto.*;
import com.example.demo.domain.product.deposit.service.DepositProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/signup")
    public String signUp(@RequestBody DepositProductSignUpRequest request) {
        depositProductService.createDepositAccount(request.getDepositAccountDTO(), request.getJoinHistoryDTO());
        return "가입이 완료되었습니다.";
    }

}
