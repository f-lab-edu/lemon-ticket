package com.flab.lemonticket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file, Long userId) throws IOException {
        // 파일 형식 제한
        String contentType = file.getContentType();
        if( !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            throw new IOException("Only JPG and PNG files are allowed");
        }

        Path uploadPath = Paths.get(uploadDir);
        if( !Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        // 파일 이름 변경
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String newFileName = generateNewFileName(userId, fileExtension);

        Path filePath = uploadPath.resolve(newFileName);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }

    private String getFileExtension(String fileName){
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ?"":fileName.substring(dotIndex);
    }

    private String generateNewFileName(Long userId, String fileExtension){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueID = UUID.randomUUID().toString();
        return userId +"_"+timestamp +"_"+uniqueID+fileExtension;
    }
}
