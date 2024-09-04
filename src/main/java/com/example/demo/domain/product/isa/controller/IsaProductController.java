package com.example.demo.domain.product.isa.controller;

import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.service.DepositProductService;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import com.example.demo.domain.product.isa.service.IsaProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="IsaProduct")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product/isa")
public class IsaProductController {
    private final IsaProductService isaProductService;

    @GetMapping("/portfolioList")
    public ResponseEntity<List<IsaPortfolioListResponse>> getPortfolio() {
        return ResponseEntity.ok(isaProductService.getPortfolio());
    }
}
