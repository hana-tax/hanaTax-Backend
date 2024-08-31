package com.example.demo.domain.product.deposit.controller;

import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.service.DepositProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
