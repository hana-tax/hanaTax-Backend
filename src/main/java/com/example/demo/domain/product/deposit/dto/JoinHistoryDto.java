package com.example.demo.domain.product.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinHistoryDto {
    private Long subscribeId;
    private Integer accountType;
    private String accountNumber;
    private Integer joinAccount;
    private Date joinDate;
    private Date expirationDate;
    private Double interest;
    private Integer interestMethod;
    private Integer joinStatus;
    private String id;
}
