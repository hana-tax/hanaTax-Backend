package com.example.demo.domain.mydata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDataResponseDto {
    private String ci;
    private String linkedAssets;
}
