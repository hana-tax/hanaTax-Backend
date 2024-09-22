package com.example.demo.domain.mydata.token.service;

import com.example.demo.domain.mydata.token.dto.UserToken;
import com.example.demo.domain.mydata.token.mapper.UserTokenMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final UserTokenMapper userTokenMapper;

    public UserToken getUserTokenById(String id) {
        return userTokenMapper.getUserTokenById(id);
    }

    public void insertUserToken(UserToken userToken) {
        userTokenMapper.insertUserToken(userToken);
    }
}
