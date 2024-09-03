package com.example.demo.domain.account.mapper;

import com.example.demo.domain.account.dto.DepositAccountListRequest;
import com.example.demo.domain.account.dto.DepositAccountListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<DepositAccountListResponse> getAccountList(DepositAccountListRequest dto);
}
