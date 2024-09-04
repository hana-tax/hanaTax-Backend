package com.example.demo.domain.product.isa.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class IsaAccountDto {
    private String isa_accountNo; // ISA_ACCOUNTNO
    private Integer accountPasswd; // ACCOUNTPASSWD
    private Date createDate; // CREATEDATE
    private Date expirationDate; // EXPIRATIONDATE
    private Integer accountStatus; // ACCOUNTSTATUS
    private String sum; // SUM
    private String balance; // BALANCE
    private Integer portfolioId; // PORTFOLIOID
    private String id; // ID
    private Integer accountType; // ACCOUNTTYPE
}
