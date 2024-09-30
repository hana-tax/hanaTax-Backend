package com.example.demo.domain.mydata.service;

import com.example.demo.domain.mydata.client.MyDataRestClient;
import com.example.demo.domain.mydata.dto.IncomeDetailDto;
import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import com.example.demo.domain.mydata.dto.MyDataResponseDto;
import com.example.demo.domain.mydata.mapper.FinancialIncomeMapper;
import com.example.demo.domain.mydata.mapper.IncomeDetailMapper;
import com.example.demo.domain.mydata.mapper.UserCiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Calendar;

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


    // 매일 아침 9시에 실행되도록 설정
    @Scheduled(cron = "0 0 9 * * ?")
    public void fetchFinancialAssetsScheduled() {
        // 1. 토큰 발급 로직
        String accessToken = getAccessToken();

        // 2. 사용자 요청 정보 생성 (필요한 데이터를 사용하여 request 생성)
        MyDataEnrollmentRequest request = new MyDataEnrollmentRequest();
        request.setUserId("사용자 ID");  // 실제 사용자 ID를 넣어야 함

        // 3. 마이데이터 서버에 요청을 보내서 financialAssets 받기
        List<Object> financialAssets = enrollUserInMyData(accessToken, request);

        // financialAssets 처리
        if (financialAssets != null && !financialAssets.isEmpty()) {
            saveFinancialData(financialAssets, request.getUserId());
        }
    }

    // 토큰 발급 로직 (OAuth나 기타 인증 방식을 통해 토큰을 발급받음)
    private String getAccessToken() {
        // 토큰 발급 요청 처리 로직을 여기에 작성하세요.
        // 예를 들어, 인증 서버에 요청하여 발급된 토큰을 반환하도록 구현.
        return "발급된 토큰 값";
    }

    public List<Object> enrollUserInMyData(String accessToken, MyDataEnrollmentRequest request) {
        String ci = userCiMapper.getUserCi(request.getUserId());
        if (ci != null) {
            request.setCi(ci);  // 사용자 CI 설정
        }

        List<Object> financialAssets = myDataRestClient.enrollUserInMyData(accessToken, request);
        if (financialAssets != null && !financialAssets.isEmpty()) {
            saveFinancialData(financialAssets, request.getUserId());
        }
        System.out.println(financialAssets);
        return financialAssets;
    }


    private Date parseDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("날짜 파싱 오류: " + e.getMessage());
            return null;
        }
    }

    private String formatDateToDatabaseFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private void saveFinancialData(List<Object> financialAssets, String userId) {
        double[] totalIncomes = new double[7]; // 2018~2024년의 총 소득 저장

        for (Object asset : financialAssets) {
            if (asset instanceof Map) {
                Map<String, Object> assetMap = (Map<String, Object>) asset;

                // 이자 소득 처리
                IncomeDetailDto interestDetail = extractInterestIncome(assetMap);
                if (interestDetail != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(interestDetail.getIncomeDate());
                    int year = calendar.get(Calendar.YEAR);
                    Integer financialIncomeId = getFinancialIncomeIdByYear(year); // 연도에 해당하는 ID 가져오기

                    if (financialIncomeId != null) {
                        // Set financialIncomeId before checking existence
                        interestDetail.setFinancialIncomeId(financialIncomeId); // ID 설정
                        System.out.println(financialIncomeId);
                        if (!incomeDetailExists(interestDetail)) { // 소득이 없을 때만 삽입
                            // 소득 합산
                            totalIncomes[year - 2018] += interestDetail.getIncomeAccount(); // 해당 연도의 소득 합산

                            // income_detail에 추가
                            incomeDetailMapper.insertIncomeDetail(interestDetail);
                        }
                    }
                }

                // 배당 소득 처리
                IncomeDetailDto dividendDetail = extractDividendIncome(assetMap);
                if (dividendDetail != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dividendDetail.getIncomeDate());
                    int year = calendar.get(Calendar.YEAR);
                    Integer financialIncomeId = getFinancialIncomeIdByYear(year); // 연도에 해당하는 ID 가져오기

                    if (financialIncomeId != null) {
                        // Set financialIncomeId before checking existence
                        dividendDetail.setFinancialIncomeId(financialIncomeId); // ID 설정

                        if (!incomeDetailExists(dividendDetail)) { // 소득이 없을 때만 삽입
                            // 소득 합산
                            totalIncomes[year - 2018] += dividendDetail.getIncomeAccount();

                            // income_detail에 추가
                            incomeDetailMapper.insertIncomeDetail(dividendDetail);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < totalIncomes.length; i++) {
            if (totalIncomes[i] > 0) { // 소득이 0보다 큰 경우에만 업데이트
                int year = 2018 + i;
                Integer financialIncomeId = getFinancialIncomeIdByYear(year);
                if (financialIncomeId != null) {
                    financialIncomeMapper.updateFinancialIncome(financialIncomeId, totalIncomes[i], isOverTax(totalIncomes[i]));
                }
            }
        }
    }

    private boolean incomeDetailExists(IncomeDetailDto incomeDetailDto) {
        System.out.println("Checking income detail exists with parameters:");
        System.out.println("financialIncomeId: " + incomeDetailDto.getFinancialIncomeId());
        System.out.println("incomeType: " + incomeDetailDto.getIncomeType());
        System.out.println("accountNumber: " + incomeDetailDto.getAccountNumber());
        System.out.println("incomeDate: " + incomeDetailDto.getIncomeDate());
        return incomeDetailMapper.findIncomeDetail(
                incomeDetailDto.getFinancialIncomeId(),
                incomeDetailDto.getIncomeType(),
                incomeDetailDto.getAccountNumber(),
                incomeDetailDto.getIncomeDate()
        ) != null;
    }
    private Integer getFinancialIncomeIdByYear(int year) {
        switch (year) {
            case 2018:
                return 1;
            case 2019:
                return 2;
            case 2020:
                return 3;
            case 2021:
                return 4;
            case 2022:
                return 5;
            case 2023:
                return 6;
            case 2024:
                return 7;
            default:
                return null; // 해당 연도에 대한 ID가 없으면 null 반환
        }
    }

    private IncomeDetailDto extractInterestIncome(Map<String, Object> asset) {
        String accountNumber = (String) asset.get("accountNo");
        Integer institutionCode = (Integer) asset.get("bankCode");
        if (institutionCode == null) {
            institutionCode = 0;
        }

        String interestDateStr = (String) asset.get("interestDate");
        Date interestDate = parseDate(interestDateStr);

        // 날짜가 유효하고 이자 소득이 0보다 큰 경우
        Double interestIncome = (Double) asset.get("interestAmount");
        if (interestIncome != null && interestIncome > 0) {

            double incomeTax = Math.floor(interestIncome * 0.14); // 소득세 절삭
            double localTax = Math.floor(incomeTax * 0.014); // 지방세 절삭

            return IncomeDetailDto.builder()
                    .financialIncomeId(null) // financialIncomeId는 null로 설정
                    .incomeType(34)  // 이자소득 코드
                    .accountNumber(accountNumber)
                    .institutionName(institutionCode)
                    .incomeAccount(interestIncome)
                    .incomeTax(incomeTax)
                    .localTax(localTax)
                    .incomeDate(interestDate)
                    .build();
        }
        return null;  // 이자 소득이 없으면 null 반환
    }

    private IncomeDetailDto extractDividendIncome(Map<String, Object> asset) {
        String accountNumber = (String) asset.get("accountNo");
        Integer institutionCode = (Integer) asset.getOrDefault("bankCode", asset.get("investCode"));
        if (institutionCode == null) {
            institutionCode = 0;
        }

        String dividendDateStr = (String) asset.get("dividendDate");
        Date dividendDate = parseDate(dividendDateStr);

        // 날짜가 유효하고 배당 소득이 0보다 큰 경우
        Double dividendIncome = (Double) asset.get("dividendAmount");
        if (dividendIncome != null && dividendIncome > 0) {

            double incomeTax = Math.floor(dividendIncome * 0.14); // 소득세 절삭
            double localTax = Math.floor(incomeTax * 0.014); // 지방세 절삭

            return IncomeDetailDto.builder()
                    .financialIncomeId(null) // financialIncomeId는 null로 설정
                    .incomeType(35)  // 배당소득 코드
                    .accountNumber(accountNumber)
                    .institutionName(institutionCode)
                    .incomeAccount(dividendIncome)
                    .incomeTax(incomeTax)
                    .localTax(localTax)
                    .incomeDate(dividendDate)
                    .build();
        }
        return null;  // 배당 소득이 없으면 null 반환
    }

    private String isOverTax(double totalIncome) {
        return totalIncome > 20000000 ? "Y" : "N";
    }

    public MyDataResponseDto getMyDataByUserId(String id) {
        return userCiMapper.getMyDataByUserId(id);
    }
}
