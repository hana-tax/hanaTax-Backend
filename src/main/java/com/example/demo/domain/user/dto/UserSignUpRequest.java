package com.example.demo.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserSignUpRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "주민등록번호를 입력해주세요.")
    private String residentNumber;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotNull(message = "우편번호를 입력해주세요.")
    private int zipCode;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
}
