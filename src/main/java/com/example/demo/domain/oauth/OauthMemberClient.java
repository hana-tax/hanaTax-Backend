package com.example.demo.domain.oauth;

import com.example.demo.domain.oauth.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
