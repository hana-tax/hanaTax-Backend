package com.example.demo.domain.user.mapper;

import com.example.demo.domain.user.entity.User;
import com.example.demo.global.util.HashUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("UPDATE users SET alertMethodEmail = CASE WHEN #{email} = 1 THEN 'Y' ELSE alertMethodEmail END, " +
            "alertMethodSMS = CASE WHEN #{sms} = 1 THEN 'Y' ELSE alertMethodSMS END " +
            "WHERE id = #{id}")
    void updateAlertTax(@Param("id") String id, @Param("email") int email, @Param("sms") int sms);

    @Select("SELECT * FROM USERS WHERE id = #{id}")
    User getUserById(String id);
}
