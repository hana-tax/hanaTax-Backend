package com.example.demo.domain.income.controller;

import com.example.demo.domain.account.dto.DepositAccountListRequest;
import com.example.demo.domain.account.dto.DepositAccountListResponse;
import com.example.demo.domain.account.service.AccountService;
import com.example.demo.domain.income.dto.IncomeIsOverTaxRequest;
import com.example.demo.domain.income.dto.IncomeIsOverTaxResponse;
import com.example.demo.domain.income.dto.IncomeListRequest;
import com.example.demo.domain.income.dto.IncomeListResponse;
import com.example.demo.domain.income.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Income")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/income")
public class IncomeController {
    private final IncomeService incomeService;
    @PostMapping("/isOverTax")
    public ResponseEntity<IncomeIsOverTaxResponse> getIncomeList(@Valid @RequestBody IncomeIsOverTaxRequest dto) throws Exception {
        return ResponseEntity.ok(incomeService.getIncomeList(dto));
    }
    @PostMapping("/incomeList")
    public ResponseEntity<List<IncomeListResponse>> getAllIncomeList(@Valid @RequestBody IncomeListRequest dto) throws Exception {
        return ResponseEntity.ok(incomeService.getAllIncomeList(dto));
    }
}
