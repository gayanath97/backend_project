package com.ems.expensebillupload;

import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ExpenseBillUploadService {

    private Path fileStoragePath;
    private String fileStorageLocation;

    public ExpenseBillUploadService(@Value("D:\\ExpenseManagementSystem_Backend\\ExpenseBills") String fileStorageLocation) {

        this.fileStorageLocation = fileStorageLocation;
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public CommonResponse storeFile(MultipartFile file) {

        CommonResponse commonResponse = new CommonResponse();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            commonResponse.setStatus(true);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }
        return commonResponse;
    }


}
