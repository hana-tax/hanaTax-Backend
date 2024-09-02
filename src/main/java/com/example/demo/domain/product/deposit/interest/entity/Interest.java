package com.example.demo.domain.product.deposit.interest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest {
    private Long depositId;
    private Long interestId;
    private Integer duration;
    private Double interestRate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
