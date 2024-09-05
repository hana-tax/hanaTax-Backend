package com.example.demo.domain.investAnalysis.controller;

import com.example.demo.domain.investAnalysis.dto.InvestAnalysisInsertRequest;
import com.example.demo.domain.investAnalysis.service.InvestAnalysisService;
import com.example.demo.domain.user.dto.UserSignUpRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "InvestAnalysis")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/investAnalysis")
public class InvestAnalysisController {
    private final InvestAnalysisService investAnalysisService;
    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InvestAnalysisInsertRequest dto) {
        investAnalysisService.insert(dto);
        return ResponseEntity.ok().build();
    }
}
