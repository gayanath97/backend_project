package com.ems.controller;

import com.ems.dto.EmployeeExpenseDTO;
import com.ems.dto.EmployeeRrDTO;
import com.ems.service.EmployeeRrService;
import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-rr")
public class EmployeeRrController {

    private EmployeeRrService employeeRrService;

    @Autowired
    public EmployeeRrController(EmployeeRrService employeeRrService) {
        this.employeeRrService = employeeRrService;
    }

    @PostMapping("/")
    public CommonResponse saveEmployeeRr(@RequestBody EmployeeRrDTO employeeRrDTO){

        return employeeRrService.saveEmployeeRr(employeeRrDTO);

    }

    @PutMapping("/update/{id}")
    public CommonResponse updateEmployeeRr(@PathVariable String id,@RequestBody EmployeeRrDTO employeeRrDTO){
        return employeeRrService.updateEmployeeRr(id,employeeRrDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteEmployeeRr(@PathVariable String id){
        return employeeRrService.deleteEmployeeRr(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return employeeRrService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return employeeRrService.getById(id);
    }

}
