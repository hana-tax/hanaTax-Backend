package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.*;

public interface UserService {
    UserLoginResponse loginUser(UserLoginRequest dto);
    void signUpUser(UserSignUpRequest dto);

    boolean idExist(UserIdcheckRequest dto);

    void updateAlertTax(String id,int email, int sms);
    UserDto getUserById(String id);
    RiskProfileDto getLatestRiskProfileById(String id);
}
