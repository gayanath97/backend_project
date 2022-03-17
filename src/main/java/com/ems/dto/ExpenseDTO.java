package com.ems.dto;

import com.ems.constant.CommonStatus;
import com.ems.entity.EmployeeExpense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpenseDTO {

    private String id;
    private String buOrDept;
    private String project;
    private String extensionNo;
    private String customer;
    private String location;
    private String billability;

    private String sta_tus;

//    private String employee;
//    private Set<ExpenseBillDTO> expenseBills;
//
//    private Set<EmployeeExpenseDTO> employeeExpenses;

}
