package com.example.demo.domain.mydata.token.controller;

import com.example.demo.domain.mydata.token.dto.TokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata/auth")
public class TokenController {

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestParam String authCode) {
        // 토큰 발급 (실제로는 인증 서버에서 발급)
        TokenResponse tokenResponse = new TokenResponse(
                "Bearer",
                "ACCESS_TOKEN_" + authCode,
                3600,
                "REFRESH_TOKEN_" + authCode,
                7200
        );

        return ResponseEntity.ok(tokenResponse);
    }
}
