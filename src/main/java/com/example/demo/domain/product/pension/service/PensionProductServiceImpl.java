package com.example.demo.domain.product.pension.service;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.isa.mapper.IsaProductMapper;
import com.example.demo.domain.product.pension.dto.PensionAccountDto;
import com.example.demo.domain.product.pension.mapper.PensionProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PensionProductServiceImpl implements PensionProductService{
    private final PensionProductMapper pensionProductMapper;

    @Override
    public void createPensionAccount(PensionAccountDto pensionAccountDto, JoinHistoryDto joinHistory) {
        // 계좌 번호 자동 생성
        String generatedAccountNo = createAccountNum();
        pensionAccountDto.setFundAccountNo(generatedAccountNo);

        pensionProductMapper.signUpForPensionAccount(pensionAccountDto);

        joinHistory.setAccountNumber(generatedAccountNo);
        pensionProductMapper.insertJoinHistory(joinHistory);
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
