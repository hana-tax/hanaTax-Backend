package com.example.demo.domain.product.deposit.interest.controller;

import com.example.demo.domain.product.deposit.interest.dto.InterestDepositProductResponse;
import com.example.demo.domain.product.deposit.interest.service.InterestDepositProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="DepositProduct")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product/deposit/interest")
public class InterestDepositProductController {
    private final InterestDepositProductService interestDepositProductService;
    @GetMapping("/{id}")
    public ResponseEntity<List<InterestDepositProductResponse>> getDepositProductDetail(@PathVariable Long id) {
        return ResponseEntity.ok(interestDepositProductService.getInterest(id));
    }
}
