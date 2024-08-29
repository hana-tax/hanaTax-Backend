package com.example.demo.domain.oauth.kakao.dto;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
@JsonNaming(SnakeCaseStrategy.class)
public record KakaoToken(
        String tokenType,
        String accessToken,
        String idToken,
        Integer expiresIn,
        String refreshToken,
        Integer refreshTokenExpiresIn,
        String scope
) {
}
