package com.example.demo.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdcheckRequest {
    @NotBlank(message = "id를 입력해주세요.")
    private String id;
}
