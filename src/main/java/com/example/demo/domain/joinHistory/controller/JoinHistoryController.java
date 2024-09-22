package com.example.demo.domain.joinHistory.controller;

import com.example.demo.domain.joinHistory.dto.AssetComparisonResponseDTO;
import com.example.demo.domain.joinHistory.dto.JoinHistoryDto;
import com.example.demo.domain.joinHistory.dto.TaxAssetTopDTO;
import com.example.demo.domain.joinHistory.dto.TotalBalanceResponseDto;
import com.example.demo.domain.joinHistory.service.JoinHistoryService;
import com.example.demo.domain.user.dto.IdRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/join-history")
public class JoinHistoryController {

    private final JoinHistoryService joinHistoryService;

    @PostMapping("/saving")
    public ResponseEntity<List<JoinHistoryDto>> getSavingAccounts(@RequestBody IdRequestDto requestDto) {
        List<JoinHistoryDto> savingAccounts = joinHistoryService.getSavingAccounts(requestDto.getId());
        return ResponseEntity.ok(savingAccounts);
    }

    @PostMapping("/isa")
    public ResponseEntity<List<JoinHistoryDto>> getIsaAccounts(@RequestBody IdRequestDto requestDto) {
        List<JoinHistoryDto> isaAccounts = joinHistoryService.getIsaAccounts(requestDto.getId());
        return ResponseEntity.ok(isaAccounts);
    }

    @PostMapping("/fund")
    public ResponseEntity<List<JoinHistoryDto>> getFundAccounts(@RequestBody IdRequestDto requestDto) {
        List<JoinHistoryDto> fundAccounts = joinHistoryService.getFundAccounts(requestDto.getId());
        return ResponseEntity.ok(fundAccounts);
    }

    @PostMapping("/total")
    public ResponseEntity<TotalBalanceResponseDto> getTotalBalance(@RequestBody IdRequestDto requestDto) {
        TotalBalanceResponseDto totalBalance = joinHistoryService.getTotalBalance(requestDto.getId());
        return ResponseEntity.ok(totalBalance);
    }

    @GetMapping("/compare/{ageGroup}")
    public ResponseEntity<AssetComparisonResponseDTO> compareAssets(@PathVariable String ageGroup, @RequestParam String userId) {
        AssetComparisonResponseDTO comparisonData = joinHistoryService.compareUserAssetsWithPeers(ageGroup, userId);
        return ResponseEntity.ok(comparisonData);
    }

    @GetMapping("/top-tax/{ageGroup}")
    public ResponseEntity<List<TaxAssetTopDTO>> getTopTaxSavingAssets(@PathVariable String ageGroup) {
        List<TaxAssetTopDTO> topAssets = joinHistoryService.getTopTaxSavingAssets(ageGroup);
        return ResponseEntity.ok(topAssets);
    }
}
