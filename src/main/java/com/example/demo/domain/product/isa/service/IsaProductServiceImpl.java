package com.example.demo.domain.product.isa.service;

import com.example.demo.domain.product.deposit.dto.DepositAccountDto;
import com.example.demo.domain.product.deposit.dto.DepositProductListResponse;
import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.deposit.mapper.DepositProductMapper;
import com.example.demo.domain.product.isa.dto.IsaAccountDto;
import com.example.demo.domain.product.isa.dto.IsaPortfolioListResponse;
import com.example.demo.domain.product.isa.mapper.IsaProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IsaProductServiceImpl implements  IsaProductService{
    private final IsaProductMapper isaProductMapper;

    @Override
    public List<IsaPortfolioListResponse> getPortfolio() {
        return isaProductMapper.getPortfolio();
    }

    @Transactional
    public void createIsaAccount(IsaAccountDto isaAccount, JoinHistoryDto joinHistory) {
        // 계좌 번호 자동 생성
        String generatedAccountNo = createAccountNum();
        isaAccount.setIsa_accountNo(generatedAccountNo);

        isaProductMapper.signUpForIsaAccount(isaAccount);

        joinHistory.setAccountNumber(generatedAccountNo);
        isaProductMapper.insertJoinHistory(joinHistory);
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
