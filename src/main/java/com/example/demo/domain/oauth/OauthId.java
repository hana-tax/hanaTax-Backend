package com.example.demo.domain.oauth;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthId {

    @Column(nullable = false, name = "oauth_server_id")
    private String oauthServerId;

    @Enumerated(STRING)
    @Column(nullable = false, name = "oauth_server")
    private OauthServerType oauthServerType;

//    // 추가된 user_id 필드
//    @Column(nullable = false, name = "user_id")
//    private String id;

    public String oauthServerId() {
        return oauthServerId;
    }

    public OauthServerType oauthServer() {
        return oauthServerType;
    }

//    public String userId() {
//        return userId;
//    }
}
