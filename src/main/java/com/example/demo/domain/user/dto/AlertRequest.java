package com.example.demo.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlertRequest {
    private List<Integer> alertMethods; //1: 이메일, 2: SMS

}
