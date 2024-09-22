package com.example.demo.domain.user.mapper;

import com.example.demo.domain.user.dto.RiskProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RiskProfileMapper {
    @Select("SELECT * FROM ( " +
            "SELECT * FROM risk_profile " +
            "WHERE id = #{id} " +
            "ORDER BY profileDate DESC) " +
            "WHERE ROWNUM = 1")
    RiskProfileDto getLatestRiskProfileById(String id);
}
