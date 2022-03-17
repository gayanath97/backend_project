package com.ems.controller;

//import com.ems.dto.EmployeeDTO;
import com.ems.dto.SignupRequest;
import com.ems.service.EmployeeService;
import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public CommonResponse saveEmployee(@RequestBody SignupRequest signUpRequest){

        return employeeService.saveEmployee(signUpRequest);

   }

   @PutMapping("/update/{employeeCode}")
    public CommonResponse updateEmployee(@PathVariable String employeeCode,@RequestBody SignupRequest signupRequest){
        return employeeService.updateEmployee(employeeCode,signupRequest);
   }

   @DeleteMapping("/{employeeCode}")
    public CommonResponse deleteEmployee(@PathVariable String employeeCode){
        return employeeService.deleteEmployee(employeeCode);
   }

   @GetMapping("/")
    public CommonResponse getAll(){
        return employeeService.getAll();
   }

   @GetMapping("/{employeeCode}")
    public CommonResponse getById(@PathVariable String employeeCode){
        return employeeService.getById(employeeCode);
   }













}
