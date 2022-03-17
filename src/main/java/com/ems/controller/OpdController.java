package com.ems.controller;

import com.ems.util.CommonResponse;
import com.ems.dto.OpdDTO;
import com.ems.service.OpdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/opd")
public class OpdController {

    private OpdService opdService;

    @Autowired
    public OpdController(OpdService opdService) {
        this.opdService = opdService;
    }

    @PostMapping("/")
    public CommonResponse saveOpd(@RequestBody OpdDTO opdDTO){
        return opdService.saveOpd(opdDTO);

    }

    @PutMapping("/update/{id}")
    public  CommonResponse updateOpd(@PathVariable String id,@RequestBody OpdDTO opdDTO){
        return opdService.updateOpd(id,opdDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteOpd(@PathVariable String id){
        return opdService.deleteOpd(id);
    }

    @GetMapping("/")
    public  CommonResponse getAll(){
        return opdService.getAll();
    }

    @GetMapping("/{id}")
    public  CommonResponse getById(@PathVariable String id){
        return opdService.getById(id);
    }


}
