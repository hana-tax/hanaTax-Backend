package com.example.demo.domain.mydata.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class MyDataEnrollmentRequest {
    private String userId;
    private String ci;
    private List<Integer> assetCodes;
}
