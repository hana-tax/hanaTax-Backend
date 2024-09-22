package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.*;
import com.example.demo.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest dto, HttpSession session) {
        UserLoginResponse response = userService.loginUser(dto);
        // 로그인 성공 시 세션에 사용자 정보 저장
        session.setAttribute("userId", response.getId());
        System.out.println(response.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserSignUpRequest dto) {
        userService.signUpUser(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/idcheck")
    public ResponseEntity<UserIdcheckResponse> checkId(
          @Valid @RequestBody UserIdcheckRequest dto) {

        UserIdcheckRequest request = new UserIdcheckRequest();
        request.setId(dto.getId());

        boolean exists = userService.idExist(request);

        UserIdcheckResponse response = new UserIdcheckResponse();
        response.setExist(exists);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/alert")
    public ResponseEntity<Void> applyAlertTax(@PathVariable String id, @RequestBody AlertRequest alertRequest) {
        int emailSelected = alertRequest.getAlertMethods().contains(1) ? 1 : 0; // 이메일 알림
        int smsSelected = alertRequest.getAlertMethods().contains(2) ? 1 : 0;   // 문자 알림

        userService.updateAlertTax(id, emailSelected, smsSelected);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById(@RequestBody IdRequestDto requestDto) {
        UserDto userDto = userService.getUserById(requestDto.getId());

        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/risk-profile")
    public ResponseEntity<RiskProfileDto> getLatestRiskProfileById(@RequestBody IdRequestDto requestDto) {
        RiskProfileDto riskProfileDto = userService.getLatestRiskProfileById(requestDto.getId());

        return ResponseEntity.ok(riskProfileDto);
    }

}
