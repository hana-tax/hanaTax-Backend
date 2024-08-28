package com.example.demo.domain.user.mapper;

import com.example.demo.domain.user.entity.User;
import com.example.demo.global.util.HashUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User findUserByLogin(@Param("id") String id, @Param("password") String password);

    void signUp(@Param("id") String id,
                @Param("password") String password,
                @Param("name") String name,
                @Param("residentNumber") String residentNumber,
                @Param("phoneNumber") String phoneNumber,
                @Param("email") String email,
                @Param("zipCode") int zipCode,
                @Param("address") String address,
                @Param("userCi") String userCi);

    @Select("SELECT COUNT(*) FROM users WHERE id = #{id}")
    int countById(String id);

}
