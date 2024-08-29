package com.example.demo.domain;

import com.example.demo.domain.oauth.OauthId;
import com.example.demo.domain.oauth.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {

    Optional<OauthMember> findByOauthId(OauthId oauthId);
    Optional<OauthMember> findBynickname(String nickname);

}
