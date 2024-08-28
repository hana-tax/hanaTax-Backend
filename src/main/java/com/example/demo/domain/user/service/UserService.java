package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserIdcheckRequest;
import com.example.demo.domain.user.dto.UserLoginRequest;
import com.example.demo.domain.user.dto.UserLoginResponse;
import com.example.demo.domain.user.dto.UserSignUpRequest;

public interface UserService {
    UserLoginResponse loginUser(UserLoginRequest dto);
    void signUpUser(UserSignUpRequest dto);

    boolean idExist(UserIdcheckRequest dto);
}
