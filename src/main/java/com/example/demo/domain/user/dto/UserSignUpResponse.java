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
    private String id;
    private String name;
    private String userCi;

    public static UserSignUpResponse of(String id, String name, String userCi) {
        return UserSignUpResponse.builder()
                .id(id)
                .name(name)
                .userCi(userCi)
                .build();
    }

}
