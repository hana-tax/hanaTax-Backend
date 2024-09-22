package com.example.demo.domain.mydata.token.mapper;

import com.example.demo.domain.mydata.token.dto.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserTokenMapper {
    @Insert("INSERT INTO user_tokens(tokenId, accessToken, refreshToken, createdDate, expiresAt, id) " +
            "VALUES(users_token_seq.NEXTVAL, #{accessToken}, #{refreshToken}, #{createdDate}, #{expiresAt}, #{id})")
    void insertUserToken(UserToken userToken);

    @Select("SELECT * FROM user_tokens WHERE id = #{id} ORDER BY createdDate DESC FETCH FIRST 1 ROWS ONLY")
    UserToken getUserTokenById(String id);
}
