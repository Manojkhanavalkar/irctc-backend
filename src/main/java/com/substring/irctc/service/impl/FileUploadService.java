package com.substring.irctc.service.impl;

import com.substring.irctc.entity.ImageMetaData;
import com.substring.irctc.helper.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService implements com.substring.irctc.service.FileUploadService {
    @Value("${file.upload.folder}")
    private String folder;
    @Override
    public ImageMetaData fileUpload(MultipartFile file) throws IOException {
        String originalFileName=file.getOriginalFilename();
        System.out.println(originalFileName);
        if(!Files.exists(Paths.get(folder))){
            Files.createDirectories(Paths.get(folder));
        }
        String fileNameWithPath= Helper.getFileName(folder,originalFileName);
        Files.copy(file.getInputStream(),Paths.get(fileNameWithPath), StandardCopyOption.REPLACE_EXISTING);

        ImageMetaData metaData=new ImageMetaData();
        metaData.setFileId(UUID.randomUUID().toString());
        metaData.setFileName(fileNameWithPath);
        metaData.setFileSize(file.getSize());
        metaData.setContentType(file.getContentType());

        return metaData;
    }
}
