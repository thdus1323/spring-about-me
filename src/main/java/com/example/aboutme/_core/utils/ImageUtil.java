package com.example.aboutme._core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class ImageUtil {

    public static String saveBase64Image(String base64Image, String uploadsDir) throws IOException {
        // Base64 문자열에서 확장자 추출
        String fileExtension = extractFileExtension(base64Image);
        if (fileExtension == null) {
            throw new IOException("Unsupported image format");
        }

        // 파일 저장 경로 설정
        String fileName = UUID.randomUUID().toString() + fileExtension;
        String filePath = Paths.get(uploadsDir, fileName).toString();

        // Base64 문자열을 디코딩하여 파일로 저장
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.write(path, decodedBytes);

        return filePath;
    }

    private static String extractFileExtension(String base64Image) {
        if (base64Image.startsWith("data:image/jpeg")) {
            return ".jpg";
        } else if (base64Image.startsWith("data:image/png")) {
            return ".png";
        } else if (base64Image.startsWith("data:image/gif")) {
            return ".gif";
        }
        return null; // 지원하지 않는 형식의 경우 null 반환
    }
}