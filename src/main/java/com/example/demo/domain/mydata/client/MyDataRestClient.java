package com.example.demo.domain.mydata.client;

import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;  // 수정된 부분
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MyDataRestClient {
    private final RestTemplate restTemplate;

    @Autowired
    public MyDataRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 마이데이터 서버에 사용자 등록 요청
    public List<Object> enrollUserInMyData(String accessToken, MyDataEnrollmentRequest request) {
        // 마이데이터 서버 URL
        String url = "http://localhost:8081/api/enroll";

        // 헤더 설정: Authorization에 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        // HttpEntity로 헤더와 요청 바디 설정
        HttpEntity<MyDataEnrollmentRequest> entity = new HttpEntity<>(request, headers);

        // 마이데이터 서버로 요청을 전송하고, 금융사 데이터를 리스트로 받음
        ResponseEntity<List<Object>> response = restTemplate.exchange(url, HttpMethod.POST, entity, (Class<List<Object>>)(Class<?>)List.class);

        // 성공적으로 받은 금융사 데이터를 반환
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;  // 실패 시 null 반환
        }
    }
}
