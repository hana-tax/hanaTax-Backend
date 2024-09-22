package com.example.demo.domain.joinHistory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalBalanceResponseDto {
    private String id;
    private Double totalBalance;
}
