package com.example.demo.domain.product.pension.dto;

import com.example.demo.domain.product.deposit.dto.JoinHistoryDto;
import com.example.demo.domain.product.isa.dto.IsaAccountDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PensionProductSignUpRequest {
    private PensionAccountDto pensionAccountDto;
    private JoinHistoryDto joinHistoryDTO;
}
