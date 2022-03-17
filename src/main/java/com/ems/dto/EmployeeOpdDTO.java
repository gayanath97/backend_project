package com.ems.dto;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeOpdDTO {


    private String id;
    private String employee;
    private String opd;

    private CommonStatus statuss;



}
