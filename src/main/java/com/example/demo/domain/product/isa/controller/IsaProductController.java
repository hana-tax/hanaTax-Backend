package com.example.demo.domain.product.isa.controller;


import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import com.example.demo.domain.product.isa.dto.IsaProductSignUpRequest;
import com.example.demo.domain.product.isa.service.IsaProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="IsaProduct")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product/isa")
public class IsaProductController {
    private final IsaProductService isaProductService;
    @PostMapping("/signup")
    public String signUp(@RequestBody IsaProductSignUpRequest request) {
        isaProductService.createIsaAccount(request.getIsaAccountDto(), request.getJoinHistoryDTO());
        return "가입이 완료되었습니다.";
    }
    @GetMapping("/portfolioList")
    public ResponseEntity<List<IsaPortfolioListResponse>> getPortfolio() {
        return ResponseEntity.ok(isaProductService.getPortfolio());
    }
}
