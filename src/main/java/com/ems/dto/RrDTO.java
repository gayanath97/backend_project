package com.ems.dto;

import com.ems.constant.CommonStatus;
import com.ems.entity.EmployeeRr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RrDTO {

    private String id;
    private String extensionNo;
    private String customer;
    private String location;
    private String sta_tus;



//    private Set<RrBillDTO> rrBills;
//
//    private Set<EmployeeRrDTO> employeeRrs;
//
//
//    private CommonStatus statuss;


}
