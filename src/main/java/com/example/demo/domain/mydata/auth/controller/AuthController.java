package com.example.demo.domain.mydata.auth.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@Tag(name = "MyData")
@RestController
@RequestMapping("/api/mydata/auth")
public class AuthController {


    private final SecretKey secretKey;

    public AuthController() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    @GetMapping("/authorize")
    public ResponseEntity<String> getAuthCode(@RequestParam String userCi) {
        // JWT 토큰 생성
        String jwt = Jwts.builder()
                .setSubject(userCi)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간 유효
                .signWith(secretKey)
                .compact();

        // 토큰 반환
        return ResponseEntity.ok(jwt);
    }
}
