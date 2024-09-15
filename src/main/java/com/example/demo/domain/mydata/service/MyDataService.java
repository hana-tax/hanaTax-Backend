package com.example.demo.domain.mydata.service;

import com.example.demo.domain.mydata.client.MyDataRestClient;
import com.example.demo.domain.mydata.dto.IncomeDetailDto;
import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import com.example.demo.domain.mydata.mapper.FinancialIncomeMapper;
import com.example.demo.domain.mydata.mapper.IncomeDetailMapper;
import com.example.demo.domain.mydata.mapper.UserCiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MyDataService {
    private final MyDataRestClient myDataRestClient;
    private final UserCiMapper userCiMapper;
    private final FinancialIncomeMapper financialIncomeMapper;
    private final IncomeDetailMapper incomeDetailMapper;

    @Autowired
    public MyDataService(MyDataRestClient myDataRestClient, UserCiMapper userCiMapper,
                         FinancialIncomeMapper financialIncomeMapper, IncomeDetailMapper incomeDetailMapper) {
        this.myDataRestClient = myDataRestClient;
        this.userCiMapper = userCiMapper;
        this.financialIncomeMapper = financialIncomeMapper;
        this.incomeDetailMapper = incomeDetailMapper;
    }

    public List<Object> enrollUserInMyData(String accessToken, MyDataEnrollmentRequest request) {
        String ci = userCiMapper.getUserCi(request.getUserId());
        request.setCi(ci);  // 사용자 CI 설정

        List<Object> financialAssets = myDataRestClient.enrollUserInMyData(accessToken, request);
        if (financialAssets != null && !financialAssets.isEmpty()) {
            saveFinancialData(financialAssets, request.getUserId());
        }

        return financialAssets;
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("날짜 파싱 오류: " + e.getMessage());
            return null;
        }
    }

    private String formatDateToDatabaseFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private IncomeDetailDto extractIncomeFromAsset(Map<String, Object> asset, int financialIncomeId) {
        String accountNumber = (String) asset.get("accountNo");
        int institutionCode = asset.containsKey("bankCode")
                ? (Integer) asset.get("bankCode")
                : (Integer) asset.get("investCode");
        String createdAtStr = (String) asset.get("createdAt");
        Date incomeDate = parseDate(createdAtStr);
        String formattedIncomeDate = formatDateToDatabaseFormat(incomeDate);

        double interestIncome = (Double) asset.getOrDefault("interestIncome", 0.0);
        double dividendIncome = (Double) asset.getOrDefault("dividendIncome", 0.0);
        double incomeTax = 0.0;
        int incomeTypeCode = 0;

        if (interestIncome > 0) {
            incomeTypeCode = 34;  // 이자소득 코드
            incomeTax = interestIncome;
        } else if (dividendIncome > 0) {
            incomeTypeCode = 35;  // 배당소득 코드
            incomeTax = dividendIncome;
        }

        double localTax = incomeTax * 0.1;  // 지방세는 소득세의 10%

        return IncomeDetailDto.builder()
                .financialIncomeId(financialIncomeId)
                .incomeType(incomeTypeCode)
                .accountNumber(accountNumber)
                .institutionName(institutionCode)  // 기관 코드로 저장
                .incomeAccount(incomeTax)
                .incomeTax(incomeTax)
                .localTax(localTax)
                .incomeDate(formattedIncomeDate)
                .build();
    }

    private void saveFinancialData(List<Object> financialAssets, String userId) {
        double totalIncome = 0;

        Integer financialIncomeId = financialIncomeMapper.getFinancialIncomeId(userId);

        for (Object asset : financialAssets) {
            Map<String, Object> assetMap = (Map<String, Object>) asset;

            IncomeDetailDto incomeDetail = extractIncomeFromAsset(assetMap, financialIncomeId);
            totalIncome += incomeDetail.getIncomeTax() + incomeDetail.getLocalTax();

            incomeDetailMapper.insertIncomeDetail(
                    incomeDetail.getIncomeId(),
                    incomeDetail.getFinancialIncomeId(),
                    incomeDetail.getIncomeType(),
                    incomeDetail.getAccountNumber(),
                    incomeDetail.getInstitutionName(),
                    incomeDetail.getIncomeAccount(),
                    incomeDetail.getIncomeTax(),
                    incomeDetail.getLocalTax(),
                    incomeDetail.getIncomeDate()
            );
        }

        if (financialIncomeId != null) {
            financialIncomeMapper.updateFinancialIncome(financialIncomeId, totalIncome, isOverTax(totalIncome));
        } else {
            financialIncomeMapper.insertFinancialIncome(totalIncome, isOverTax(totalIncome), userId);
        }
    }

    private String isOverTax(double totalIncome) {
        return totalIncome > 20000000 ? "Y" : "N";
    }
}
