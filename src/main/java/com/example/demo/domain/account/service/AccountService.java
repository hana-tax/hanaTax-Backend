package com.example.demo.domain.account.service;

import com.example.demo.domain.account.dto.DepositAccountListRequest;
import com.example.demo.domain.account.dto.DepositAccountListResponse;

import java.util.List;

public interface AccountService {
    List<DepositAccountListResponse> getAccountList(DepositAccountListRequest dto);
}
