package com.example.demo.domain.mydata.token.controller;

import com.example.demo.domain.mydata.token.dto.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata/auth")
public class TokenController {

    private final SecretKey secretKey;  // JWT 서명에 사용할 키

    public TokenController(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestParam String authCode) {
        // 현재 시간
        long now = System.currentTimeMillis();

        // 액세스 토큰 발급 (유효기간 1시간)
        String accessToken = Jwts.builder()
                .setSubject(authCode)  // 토큰에 authCode를 subject로 설정
                .setIssuedAt(new Date(now))  // 발급 시간
                .setExpiration(new Date(now + 3600 * 1000))  // 만료 시간 (1시간)
                .signWith(secretKey, SignatureAlgorithm.HS256)  // 서명 알고리즘과 secretKey 사용
                .compact();

        // 리프레시 토큰 발급 (유효기간 2시간)
        String refreshToken = Jwts.builder()
                .setSubject(authCode)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 7200 * 1000))  // 만료 시간 (2시간)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // TokenResponse 생성
        TokenResponse tokenResponse = new TokenResponse(
                "Bearer",
                accessToken,
                3600,  // 액세스 토큰 유효기간 (1시간)
                refreshToken,
                7200   // 리프레시 토큰 유효기간 (2시간)
        );

        return ResponseEntity.ok(tokenResponse);
    }
}
