package com.e.d.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyCloudService {

	public void upload(MultipartFile file) throws IllegalStateException, IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("파일이 비어 있습니다.");
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        String fileName = timestamp + "_" + uuid + "_" + file.getOriginalFilename();

        String basePath = System.getProperty("user.home") + "/Desktop/mycloud/";

        File uploadDir = new File(basePath);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                throw new IOException("업로드 폴더를 생성할 수 없습니다: " + basePath);
            }
        }

        // 파일 저장
        File destFile = new File(uploadDir, fileName);
        try (InputStream inputStream = file.getInputStream(); FileOutputStream outputStream = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024 * 8]; // 8KB 버퍼
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("파일 저장 완료 : " + destFile.getAbsolutePath());
        System.out.println("----------------------------------------------------");
    }
	
}
