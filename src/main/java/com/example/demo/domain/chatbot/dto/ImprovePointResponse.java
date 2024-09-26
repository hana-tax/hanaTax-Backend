package com.example.demo.domain.chatbot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImprovePointResponse {
    private List<ImprovementDetail> improvementResponse;
}