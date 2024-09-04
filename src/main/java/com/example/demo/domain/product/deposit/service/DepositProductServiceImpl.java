package com.example.demo.domain.product.deposit.service;

import com.example.demo.domain.product.deposit.dto.DepositAccountDto;
import com.example.demo.domain.product.deposit.dto.DepositProductDetailResponse;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositProductServiceImpl implements DepositProductService {

    private final DepositProductMapper depositProductMapper;

    @Override
    public List<DepositProductListResponse> getFinancialProductList() {
        return depositProductMapper.getDepositProductList();
    }

    @Override
    public DepositProductDetailResponse getDepositProductDetail(Long id) {
        return depositProductMapper.getDepositProductDetail(id);
    }

    @Transactional
    public void createDepositAccount(DepositAccountDto depositAccount, JoinHistoryDto joinHistory) {
        // 계좌 번호 자동 생성
        String generatedAccountNo = createAccountNum();
        depositAccount.setDepositAccountId(generatedAccountNo);

        depositProductMapper.signUpForDepositAccount(depositAccount);

        joinHistory.setAccountNumber(generatedAccountNo);
        depositProductMapper.insertJoinHistory(joinHistory);
    }
    private int counter = 1;
    private String createAccountNum() {
        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        String randomNum = "";

        // 5자리의 랜덤 숫자 생성
        for (int i = 0; i < 5; i++) {
            createNum = random.nextInt(10); // 0부터 9까지의 숫자 생성
            ranNum = Integer.toString(createNum);
            randomNum += ranNum;
        }

        // 은행 코드 (고정 값)
        String onlyBankNum = "081";

        // 6자리의 카운터 기반 숫자 생성
        String countAccountNum = String.format("%06d", counter);

        // 카운터 값 증가
        counter++;

        String accountNum = onlyBankNum + "-" + countAccountNum + "-" + randomNum;
        return accountNum;
    }

}
