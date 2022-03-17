package com.ems.controller;

import com.ems.util.CommonResponse;
import com.ems.dto.OpdBillDTO;
import com.ems.service.OpdBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/opdbill")
public class OpdBillController {


    private OpdBillService opdBillService;

    @Autowired
    public OpdBillController(OpdBillService opdBillService) {
        this.opdBillService = opdBillService;
    }

    @PostMapping("/")
    public CommonResponse saveOpdBill(@RequestBody OpdBillDTO opdBillDTO){
        return opdBillService.saveOpdBill(opdBillDTO);
    }
    @PutMapping("/update/{id}")
    public CommonResponse updateOpdBill(@PathVariable String id,@RequestBody OpdBillDTO opdBillDTO){
        return opdBillService.updateOpdBill(id,opdBillDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteOpdBill(@PathVariable String id){
        return opdBillService.deleteOpdBill(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return opdBillService.getAll();
    }
    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return opdBillService.getById(id);
    }


}
