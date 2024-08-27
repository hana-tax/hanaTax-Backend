package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserLoginRequest;
import com.example.demo.domain.user.dto.UserLoginResponse;
import com.example.demo.domain.user.dto.UserSignUpRequest;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.mapper.UserMapper;
import com.example.demo.global.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    @Override
    public UserLoginResponse loginUser(UserLoginRequest dto) {
        User user = userMapper.findUserByLogin(dto.getId(), dto.getPassword());
        return UserLoginResponse.of(user.getUserId(), user.getName(),
                user.getUserCi());
    }

    @Override
    public void signUpUser(UserSignUpRequest dto) {
        String userCi = HashUtil.hashResidentNumber(dto.getResidentNumber());
        userMapper.signUp(dto.getId(), dto.getPassword(), dto.getName(),
                dto.getResidentNumber(), dto.getPhoneNumber(), dto.getEmail(),dto.getZipCode(),dto.getAddress(),userCi);
    }

}
