package com.example.demo.domain.mydata.token.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserToken {
    private Long tokenId;
    private String accessToken;
    private String refreshToken;
    private Date createdDate;
    private Date expiresAt;
    private String id;
}
