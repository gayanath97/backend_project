package com.ems.opdbillupload;

import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OpdBillUploadController {

    private OpdBillUploadService opdBillUploadService;

    @Autowired
    public OpdBillUploadController(OpdBillUploadService opdBillUploadService) {
        this.opdBillUploadService = opdBillUploadService;
    }


    @PostMapping("/upload-opd-bill")
    public CommonResponse singleFileUplaod(@RequestParam("file") MultipartFile file) {

        return opdBillUploadService.storeFile(file);
    }


}
