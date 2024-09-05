package com.example.demo.domain.investAnalysis.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class InvestAnalysisInsertRequest {
    private Long profileId;
    private Long profileName;
    private Date profileDate;
    private Long profileScore;
    private String id;
}
