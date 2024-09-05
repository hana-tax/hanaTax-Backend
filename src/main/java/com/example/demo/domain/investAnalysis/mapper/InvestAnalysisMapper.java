package com.example.demo.domain.investAnalysis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface InvestAnalysisMapper {
    void insert(@Param("profileId") Long profileId,
                @Param("profileName") Long profileName,
                @Param("profileDate") Date profileDate,
                @Param("profileScore") Long profileScore,
                @Param("id") String id);
}
