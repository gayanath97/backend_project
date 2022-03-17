package com.ems.entity;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buOrDept;

    private  String project;

    private Integer extensionNo;

    private String customer;

    private String location;

    private String billability;

    private String sta_tus;

    @OneToMany(mappedBy = "expense")
    Set<ExpenseBill> expenseBills;

//    @OneToMany
//    private Set<EmployeeExpense> employeeExpenses;

}
