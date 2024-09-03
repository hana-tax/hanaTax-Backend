package com.example.demo.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositAccountListResponse {
    private String depositAccountId;
    private Integer accountPasswd;
    private Integer accountStatus;
    private Double balance;
    private Date createDate;
    private Date expirationDate;
    private Integer depositId;
    private String id;
    private Integer accountType;
    public static DepositAccountListResponse of(String depositAccountId, Integer accountPasswd, Integer accountStatus, Double balance, Date createDate, Date expirationDate, Integer depositId, String id, Integer accountType) {
        return DepositAccountListResponse.builder()
                .depositAccountId(depositAccountId)
                .accountPasswd(accountPasswd)
                .accountStatus(accountStatus)
                .balance(balance)
                .createDate(createDate)
                .expirationDate(expirationDate)
                .depositId(depositId)
                .id(id)
                .accountType(accountType)
                .build();
    }

}
