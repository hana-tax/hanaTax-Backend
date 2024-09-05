package com.example.demo.domain.investAnalysis.service;

import com.example.demo.domain.investAnalysis.dto.InvestAnalysisInsertRequest;
import com.example.demo.domain.investAnalysis.mapper.InvestAnalysisMapper;
import com.example.demo.domain.user.dto.UserSignUpRequest;
import com.example.demo.domain.user.mapper.UserMapper;
import com.example.demo.global.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestAnalysisServiceImpl implements InvestAnalysisService{
    private final InvestAnalysisMapper investAnalysisMapper;

    @Override
    public void insert(InvestAnalysisInsertRequest dto) {
        investAnalysisMapper.insert(dto.getProfileId(),dto.getProfileName(),dto.getProfileDate(),dto.getProfileScore(),dto.getId());
    }

}
