package com.ems.controller;

import com.ems.dto.EmployeeExpenseDTO;
import com.ems.dto.EmployeeOpdDTO;
import com.ems.service.EmployeeOpdService;
import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-opd")
public class EmployeeOpdController {

    private EmployeeOpdService employeeOpdService;

    @Autowired
    public EmployeeOpdController(EmployeeOpdService employeeOpdService) {
        this.employeeOpdService = employeeOpdService;
    }

    @PostMapping("/")
    public CommonResponse saveEmployeeOpd(@RequestBody EmployeeOpdDTO employeeOpdDTO){

        return employeeOpdService.saveEmployeeOpd(employeeOpdDTO);

    }

    @PutMapping("/update/{id}")
    public CommonResponse updateEmployeeOpd(@PathVariable String id,@RequestBody EmployeeOpdDTO employeeOpdDTO){
        return employeeOpdService.updateEmployeeOpd(id,employeeOpdDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteEmployeeOpd(@PathVariable String id){
        return employeeOpdService.deleteEmployeeOpd(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return employeeOpdService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return employeeOpdService.getById(id);
    }

}
