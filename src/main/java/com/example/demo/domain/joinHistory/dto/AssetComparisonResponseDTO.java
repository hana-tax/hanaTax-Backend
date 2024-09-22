package com.example.demo.domain.joinHistory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetComparisonResponseDTO {
    private PeerAssetsDTO peerAssets;
    private UserAssetsDTO userAssets;
    private int difference;
}
