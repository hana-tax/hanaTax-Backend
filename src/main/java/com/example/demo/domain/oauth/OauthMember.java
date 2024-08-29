package com.example.demo.domain.oauth;

import com.example.demo.domain.oauth.OauthId;
import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "oauth_id_unique",
                        columnNames = {
                                "oauth_server_id",
                                "oauth_server"
                        }
                ),
        }
)
@Getter
@Setter
public class OauthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;

    private String nickname;
    private String profileImageUrl;
//    private String userId;

    public Long id() {
        return id;
    }

    public OauthId oauthId() {
        return oauthId;
    }

    public String nickname() {
        return nickname;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }

    // user_id에 대한 getter 메서드 추가
//    public String userId() {
//        return oauthId != null ? oauthId.userId() : null;
//    }
}
