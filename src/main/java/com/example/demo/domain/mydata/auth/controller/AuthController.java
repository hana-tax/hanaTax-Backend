package com.example.demo.domain.mydata.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata/auth")
public class AuthController {
    @GetMapping("/authorize")
    public ResponseEntity<String> getAuthCode(@RequestParam String userCi) {
        // 임의의 authCode 발급 (실제로는 인증 서버에서 발급)
        String authCode = "AUTH_CODE_" + userCi;

        // 발급된 authCode 반환
        return ResponseEntity.ok(authCode);
    }
}
