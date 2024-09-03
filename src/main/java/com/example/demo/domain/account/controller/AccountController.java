package com.example.demo.domain.account.controller;

import com.example.demo.domain.account.dto.DepositAccountListRequest;
import com.example.demo.domain.account.dto.DepositAccountListResponse;
import com.example.demo.domain.account.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/depositList")
    public ResponseEntity<List<DepositAccountListResponse>> getAllAccountList(@Valid @RequestBody DepositAccountListRequest dto) throws Exception {
        return ResponseEntity.ok(accountService.getAccountList(dto));
    }
}
