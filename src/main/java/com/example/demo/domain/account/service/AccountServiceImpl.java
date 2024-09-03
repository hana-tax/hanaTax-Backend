package com.example.demo.domain.account.service;

import com.example.demo.domain.account.dto.DepositAccountListRequest;
import com.example.demo.domain.account.dto.DepositAccountListResponse;
import com.example.demo.domain.account.mapper.AccountMapper;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountMapper accountMapper;
    @Override
    public List<DepositAccountListResponse> getAccountList(DepositAccountListRequest dto) {
        return accountMapper.getAccountList(dto);
    }
}
