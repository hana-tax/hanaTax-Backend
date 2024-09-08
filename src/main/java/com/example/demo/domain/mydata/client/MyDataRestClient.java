package com.example.demo.domain.mydata.client;

import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MyDataRestClient {
    private final RestTemplate restTemplate;

    public MyDataRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 마이데이터 서버에 사용자 등록 요청
    public List<Object> enrollUserInMyData(MyDataEnrollmentRequest request) {
        // 마이데이터 서버 URL
        String url = "http://localhost:8081/api/enroll";

        // 마이데이터 서버로 요청을 전송하고, 금융사 데이터를 리스트로 받음
        ResponseEntity<List> response = restTemplate.postForEntity(url, request, List.class);

        // 성공적으로 받은 금융사 데이터를 반환
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;  // 실패 시 null 반환
        }
    }
}
