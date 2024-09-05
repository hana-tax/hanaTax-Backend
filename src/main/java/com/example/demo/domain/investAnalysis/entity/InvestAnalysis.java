package com.example.demo.domain.investAnalysis.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class InvestAnalysis {
    private Long profileId;
    private Long profileName;
    private Date profileDate;
    private Long profileScore;
    private String id;
}
