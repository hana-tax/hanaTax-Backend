package com.example.demo.domain.income.service;
import com.example.demo.domain.income.dto.IncomeIsOverTaxRequest;
import com.example.demo.domain.income.dto.IncomeIsOverTaxResponse;
import com.example.demo.domain.income.dto.IncomeListRequest;
import com.example.demo.domain.income.dto.IncomeListResponse;
import com.example.demo.domain.income.mapper.IncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    private final IncomeMapper incomeMapper;

    @Autowired
    public IncomeService(IncomeMapper incomeMapper) {
        this.incomeMapper = incomeMapper;
    }

public IncomeIsOverTaxResponse getIncomeList(IncomeIsOverTaxRequest dto) {
        return incomeMapper.getIncomeListById(dto.getId());
}
}
