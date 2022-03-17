package com.ems.dto;

import com.ems.constant.CommonStatus;
import com.ems.entity.EmployeeOpd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpdDTO {

    private String id;
    private String amount;
    private String particulars;
    private String date;
    private String sta_tus;

    //private String employee;

//    private Set<OpdBillDTO> opdBills;


//    private CommonStatus statuss;

//    private Set<EmployeeOpdDTO> employeeOpds;


}
