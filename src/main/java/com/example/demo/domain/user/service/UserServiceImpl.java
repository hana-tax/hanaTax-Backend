package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.*;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.mapper.RiskProfileMapper;
import com.example.demo.domain.user.mapper.UserMapper;
import com.example.demo.global.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final RiskProfileMapper riskProfileMapper;
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
    public void updateAlertTax(String id, int email, int sms) {
        userMapper.updateAlertTax(id, email,sms);

    }
    @Override
    public UserDto getUserById(String id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setJoinDate(user.getJoinDate());
        userDto.setUserState(user.getUserState());
        userDto.setZipCode(user.getZipCode());
        userDto.setAddress(user.getAddress());
        userDto.setUserCi(user.getUserCi());
        userDto.setResidentNumber(user.getResidentNumber());
        userDto.setMyDataId(user.getMyDataId());
        userDto.setAlertMethodSMS(user.isAlertMethodSMS() ? 'Y' : 'N');
        userDto.setAlertMethodEmail(user.isAlertMethodEmail() ? 'Y' : 'N');

        return userDto;
    }
    @Override
    public RiskProfileDto getLatestRiskProfileById(String id) {
        return riskProfileMapper.getLatestRiskProfileById(id);
    }
}
