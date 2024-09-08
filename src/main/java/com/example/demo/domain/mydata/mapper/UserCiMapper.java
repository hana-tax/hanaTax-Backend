package com.example.demo.domain.mydata.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserCiMapper {
    @Select("SELECT userCi FROM users WHERE id = #{id}")
    String getUserCi(String id);  // SQL 쿼리로 사용자 CI 조회
}
