package com.example.demo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpResponse {
    private String userId;
    private String name;
    private String userCi;

    public static UserSignUpResponse of(String userId, String name, String userCi) {
        return UserSignUpResponse.builder()
                .userId(userId)
                .name(name)
                .userCi(userCi)
                .build();
    }

}
