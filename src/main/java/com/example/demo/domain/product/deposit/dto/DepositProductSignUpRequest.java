package com.example.demo.domain.product.deposit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositProductSignUpRequest {
    private DepositAccountDto depositAccountDTO;
    private JoinHistoryDto joinHistoryDTO;

}
