package com.example.demo.domain.mydata.token.controller;

import com.example.demo.domain.mydata.token.dto.TokenResponse;
import com.example.demo.domain.mydata.token.dto.UserToken;
import com.example.demo.domain.mydata.token.service.TokenService;
import com.example.demo.domain.user.dto.IdRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata/auth")
@RequiredArgsConstructor
public class TokenController {

    private final SecretKey secretKey;  // JWT 서명에 사용할 키
    private final TokenService tokenService; // TokenService 주입

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestParam String userId, @RequestParam String authCode) {
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

        // UserToken 객체 생성 및 저장
        UserToken userToken = new UserToken();
        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);
        userToken.setCreatedDate(new Date()); // CREATEDDATE
        userToken.setExpiresAt(new Date(now + 3600 * 1000)); // EXPIRESAT
        userToken.setId(userId); // userId

        tokenService.insertUserToken(userToken);  // Service 레이어에서 UserToken 저장 처리

        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/get-token")
    public ResponseEntity<UserToken> getUserTokenById(@RequestBody IdRequestDto requestDto) {
        UserToken userToken = tokenService.getUserTokenById(requestDto.getId()); // Service 인스턴스를 사용하여 호출

        return ResponseEntity.ok(userToken);
    }
}
