package com.example.demo.domain.product.deposit.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class DepositAccountDto {
    private String depositAccountId;
    private Integer accountPasswd;
    private Integer accountStatus;
    private Integer balance;
    private Date createDate;
    private Date expirationDate;
    private Integer depositId;
    private String id;
    private Integer accountType;
}
