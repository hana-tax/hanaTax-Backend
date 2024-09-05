package com.example.demo.domain.product.pension.controller;

import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import com.example.demo.domain.product.isa.dto.IsaProductSignUpRequest;
import com.example.demo.domain.product.isa.service.IsaProductService;
import com.example.demo.domain.product.pension.dto.PensionProductSignUpRequest;
import com.example.demo.domain.product.pension.service.PensionProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="PensionProduct")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product/pension")
public class PensionProductController {
    private final PensionProductService pensionProductService;
    @PostMapping("/signup")
    public String signUp(@RequestBody PensionProductSignUpRequest request) {
        pensionProductService.createPensionAccount(request.getPensionAccountDto(), request.getJoinHistoryDTO());
        return "가입이 완료되었습니다.";
    }
}