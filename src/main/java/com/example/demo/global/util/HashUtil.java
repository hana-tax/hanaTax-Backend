package com.example.demo.global.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String hashResidentNumber(String residentNumber) {
        try {
            // SHA-256 해시 생성기
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 주민번호를 바이트 배열로 변환하고 해시 생성
            byte[] hashBytes = digest.digest(residentNumber.getBytes());
            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("해시 알고리즘을 찾을 수 없습니다.", e);
        }
    }
    public static String hashPassword(String password) {
        try {
            // SHA-256 해시 생성기
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 주민번호를 바이트 배열로 변환하고 해시 생성
            byte[] hashBytes = digest.digest(password.getBytes());
            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("해시 알고리즘을 찾을 수 없습니다.", e);
        }
    }
}
