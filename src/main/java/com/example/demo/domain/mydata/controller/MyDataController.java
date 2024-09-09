package com.example.demo.domain.mydata.controller;

import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import com.example.demo.domain.mydata.service.MyDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata")
public class MyDataController {
    private final MyDataService myDataService;

    @Autowired
    public MyDataController(MyDataService myDataService) {
        this.myDataService = myDataService;
    }

    // 사용자 데이터를 마이데이터 서버로 보내고, 금융사 데이터를 받아오는 API
    @PostMapping("/enroll")
    public ResponseEntity<List<Object>> enrollUserInMyData( @RequestHeader("Authorization") String accessToken,@RequestBody MyDataEnrollmentRequest request) {
        // MyDataService에서 처리된 데이터를 받음
        List<Object> financialAssets = myDataService.enrollUserInMyData(accessToken, request);

        if (financialAssets != null && !financialAssets.isEmpty()) {
            return ResponseEntity.ok(financialAssets);
        } else {
            return ResponseEntity.status(500).body(null); // 처리 실패 시 500 에러
        }
    }
}
