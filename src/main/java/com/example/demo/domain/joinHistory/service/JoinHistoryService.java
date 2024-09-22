package com.example.demo.domain.joinHistory.service;

import com.example.demo.domain.joinHistory.dto.*;
import com.example.demo.domain.joinHistory.mapper.JoinHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JoinHistoryService {
    private final JoinHistoryMapper joinHistoryMapper;

    // 예적금 계좌
    public List<JoinHistoryDto> getSavingAccounts(String id) {
        return joinHistoryMapper.getSavingAccountsById(id);
    }

    // ISA 계좌
    public List<JoinHistoryDto> getIsaAccounts(String id) {
        return joinHistoryMapper.getIsaAccountsById(id);
    }

    // 펀드 계좌
    public List<JoinHistoryDto> getFundAccounts(String id) {
        return joinHistoryMapper.getFundAccountsById(id);
    }

    public TotalBalanceResponseDto getTotalBalance(String id) {
        return new TotalBalanceResponseDto(id, joinHistoryMapper.getTotalBalance(id));
    }

    public AssetComparisonResponseDTO compareUserAssetsWithPeers(String ageGroup, String userId) {
        int[] birthYearRange = calculateBirthYearRange(ageGroup);

        PeerAssetsDTO peerAssets = joinHistoryMapper.getPeerAssetsByAgeGroup(birthYearRange[0], birthYearRange[1]);

        UserAssetsDTO userAssets = joinHistoryMapper.getUserAssetsById(userId);

        AssetComparisonResponseDTO responseDTO = calculateComparison(peerAssets, userAssets);
        return responseDTO;
    }

    private int[] calculateBirthYearRange(String ageGroup) {
        int startYear = 0, endYear = 0;
        switch (ageGroup) {
            case "20s":
                startYear = 1995; endYear = 2004;
                break;
            case "30s":
                startYear = 1984; endYear = 1993;
                break;
            case "40s":
                startYear = 1974; endYear = 1983;
                break;
            // 추가적인 연령대 처리
        }
        return new int[]{startYear, endYear};
    }

    private AssetComparisonResponseDTO calculateComparison(PeerAssetsDTO peerAssets, UserAssetsDTO userAssets) {
        AssetComparisonResponseDTO response = new AssetComparisonResponseDTO();
        response.setPeerAssets(peerAssets);
        response.setUserAssets(userAssets);
        int difference = userAssets.getTotalAssets() - peerAssets.getTotalAssets();
        response.setDifference(difference);

        return response;
    }

    public List<TaxAssetTopDTO> getTopTaxSavingAssets(String ageGroup) {
        int[] birthYearRange = calculateBirthYearRange(ageGroup);
        return joinHistoryMapper.getTopTaxSavingAssetsByAgeGroup(birthYearRange[0], birthYearRange[1]);
    }

}
