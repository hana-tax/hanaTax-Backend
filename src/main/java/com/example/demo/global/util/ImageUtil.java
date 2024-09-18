package com.example.demo.global.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageUtil {
    public static String encodeImageToBase64(String imagePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(imagePath));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
