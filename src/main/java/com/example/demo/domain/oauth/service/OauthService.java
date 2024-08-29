package com.example.demo.domain.oauth.service;

import com.example.demo.domain.OauthMemberRepository;
import com.example.demo.domain.oauth.OauthMember;
import com.example.demo.domain.oauth.OauthServerType;
import com.example.demo.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import com.example.demo.domain.oauth.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

//    public Long login(OauthServerType oauthServerType, String authCode) {
//        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
//        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
//                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
//        return saved.id();
//    }

    public String login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findBynickname(oauthMember.nickname())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return saved.nickname();
    }
}
