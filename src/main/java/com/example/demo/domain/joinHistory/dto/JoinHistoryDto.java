package com.example.demo.domain.joinHistory.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JoinHistoryDto {
    private Long subscribeId;
    private int accountType;
    private String accountNumber;
    private int joinAccount;
    private Date joinDate;
    private Date expirationDate;
    private double interest;
    private int interestMethod;
    private int joinStatus;
    private String id;
}
