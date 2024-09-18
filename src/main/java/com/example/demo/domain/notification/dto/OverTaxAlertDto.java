package com.example.demo.domain.notification.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OverTaxAlertDto {
    private int alertId;
    private int alertMethod; // 알림 방법
    private Date alertDate; // 알림 날짜
    private int alertState; // 알림 상태
    private String id; // 사용자 ID

}
