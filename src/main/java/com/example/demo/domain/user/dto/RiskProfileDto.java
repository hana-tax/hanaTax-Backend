package com.example.demo.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RiskProfileDto {
    private int profileId;
    private int profileName;
    private Date profileDate;
    private int profileScore;
    private String id;
}
