package com.example.demo.domain.product.isa.dto;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IsaProductSignUpRequest {
    private IsaAccountDto isaAccountDto;
    private JoinHistoryDto joinHistoryDTO;
}

