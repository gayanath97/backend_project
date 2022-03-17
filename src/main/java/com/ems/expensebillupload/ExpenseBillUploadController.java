package com.ems.expensebillupload;

import com.ems.opdbillupload.OpdBillUploadService;
import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExpenseBillUploadController {

    private ExpenseBillUploadService expenseBillUploadService;

    @Autowired
    public ExpenseBillUploadController(ExpenseBillUploadService expenseBillUploadService) {
        this.expenseBillUploadService = expenseBillUploadService;
    }


    @PostMapping("/upload-expense-bill")
    public CommonResponse singleFileUplaod(@RequestParam("file") MultipartFile file) {

        return expenseBillUploadService.storeFile(file);
    }

}
