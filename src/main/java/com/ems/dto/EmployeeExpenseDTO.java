package com.ems.dto;

import com.ems.constant.CommonStatus;
import com.ems.entity.Employee;
import com.ems.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeExpenseDTO {


    private String id;
    private String employee;
    private String expense;

    private CommonStatus statuss;

}
