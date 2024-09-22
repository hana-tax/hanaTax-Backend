package com.example.demo.domain.joinHistory.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeerAssetsDTO {
    private int safeAssets;
    private int investmentAssets;
    private int pensionAssets;
    private int foreignAssets;
    private int taxSavingAssets;

    public int getTotalAssets() {
        return safeAssets + investmentAssets + pensionAssets + foreignAssets + taxSavingAssets;
    }
}
