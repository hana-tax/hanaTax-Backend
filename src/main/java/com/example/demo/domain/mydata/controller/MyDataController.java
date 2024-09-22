package com.example.demo.domain.mydata.controller;

import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import com.example.demo.domain.mydata.dto.MyDataResponseDto;
import com.example.demo.domain.mydata.service.MyDataService;
import com.example.demo.domain.user.dto.IdRequestDto;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata")
public class MyDataController {
    private final MyDataService myDataService;
    private final JwtUtil jwtUtil;

    @Autowired
    public MyDataController(MyDataService myDataService, SecretKey secretKey) {
        this.myDataService = myDataService;
        this.jwtUtil = new JwtUtil(secretKey);
    }

    // 사용자 데이터를 마이데이터 서버로 보내고, 금융사 데이터를 받아오는 API
    @PostMapping("/enroll")
    public ResponseEntity<List<Object>> enrollUserInMyData( @RequestHeader("Authorization") String accessToken,@RequestBody MyDataEnrollmentRequest request) {

        String token = accessToken.replace("Bearer ", "");

        // 토큰 유효성 검증
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body(null); // 인증 실패
        }

        List<Object> financialAssets = myDataService.enrollUserInMyData(accessToken, request);

        if (financialAssets != null && !financialAssets.isEmpty()) {
            return ResponseEntity.ok(financialAssets);
        } else {
            return ResponseEntity.status(500).body(null); // 처리 실패 시 500 에러
        }
    }

    @PostMapping("/linked-assets")
    public ResponseEntity<MyDataResponseDto> getMyDataByUserId(@RequestBody IdRequestDto requestDto) {
        MyDataResponseDto response = myDataService.getMyDataByUserId(requestDto.getId());
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
