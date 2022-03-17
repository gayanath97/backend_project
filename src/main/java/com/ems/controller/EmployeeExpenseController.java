package com.ems.controller;

//import com.ems.dto.EmployeeDTO;
import com.ems.dto.EmployeeExpenseDTO;
import com.ems.service.EmployeeExpenseService;
import com.ems.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-expense")
public class EmployeeExpenseController {

    private EmployeeExpenseService employeeExpenseService;


    @Autowired
    public EmployeeExpenseController(EmployeeExpenseService employeeExpenseService) {
        this.employeeExpenseService = employeeExpenseService;
    }

    @PostMapping("/")
    public CommonResponse saveEmployeeExpense(@RequestBody EmployeeExpenseDTO employeeExpenseDTO){

        return employeeExpenseService.saveEmployeeExpense(employeeExpenseDTO);

    }

    @PutMapping("/update/{id}")
    public CommonResponse updateEmployeeExpense(@PathVariable String id,@RequestBody EmployeeExpenseDTO employeeExpenseDTO){
        return employeeExpenseService.updateEmployeeExpense(id,employeeExpenseDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteEmployeeExpense(@PathVariable String id){
        return employeeExpenseService.deleteEmployeeExpense(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return employeeExpenseService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return employeeExpenseService.getById(id);
    }



}
