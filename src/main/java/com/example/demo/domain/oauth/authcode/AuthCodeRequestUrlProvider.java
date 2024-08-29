package com.example.demo.domain.oauth.authcode;

import com.example.demo.domain.oauth.OauthServerType;

public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();
    String provide();
}
