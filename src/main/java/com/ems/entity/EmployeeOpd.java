package com.ems.entity;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class EmployeeOpd {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "opd_id")
    private Opd opd;

    @Enumerated
    private CommonStatus statuss;



}
