package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserIdcheckRequest;
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
        String hashedPassword = HashUtil.hashPassword(dto.getPassword());
        User user = userMapper.findUserByLogin(dto.getId(), hashedPassword);
        return UserLoginResponse.of(user.getId(), user.getName(),
                user.getUserCi());
    }

    @Override
    public void signUpUser(UserSignUpRequest dto) {
        String userCi = HashUtil.hashResidentNumber(dto.getResidentNumber());
        String hashedPassword = HashUtil.hashPassword(dto.getPassword());
        userMapper.signUp(dto.getId(), hashedPassword, dto.getName(),
                dto.getResidentNumber(), dto.getPhoneNumber(), dto.getEmail(),dto.getZipCode(),dto.getAddress(),userCi);
    }

    @Override
    public boolean idExist(UserIdcheckRequest dto) {
        int count = userMapper.countById(dto.getId());
        return count > 0;
    }

    @Override
    public void applyAlertTax(String userId)
    {
        userMapper.updateAlertTax(userId);
    }
}
