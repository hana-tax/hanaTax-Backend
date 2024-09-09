package com.example.demo.domain.mydata.service;

import com.example.demo.domain.mydata.client.MyDataRestClient;
import com.example.demo.domain.mydata.dto.MyDataEnrollmentRequest;
import com.example.demo.domain.mydata.mapper.UserCiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyDataService {
    private final MyDataRestClient myDataRestClient;
    private final UserCiMapper userCiMapper;

    @Autowired
    public MyDataService(MyDataRestClient myDataRestClient, UserCiMapper userCiMapper) {
        this.myDataRestClient = myDataRestClient;
        this.userCiMapper = userCiMapper;
    }

    public List<Object> enrollUserInMyData(String accessToken, MyDataEnrollmentRequest request) {
        // DB에서 사용자 CI 가져오기
        String ci = userCiMapper.getUserCi(request.getUserId());
        request.setCi(ci);  // 요청에 CI 정보 설정

        // 마이데이터 서버로 사용자 등록 요청 및 금융사 데이터 가져오기
        return myDataRestClient.enrollUserInMyData(accessToken,request);
    }
}
