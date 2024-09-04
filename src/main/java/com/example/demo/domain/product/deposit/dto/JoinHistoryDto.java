package com.example.demo.domain.product.deposit.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
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
