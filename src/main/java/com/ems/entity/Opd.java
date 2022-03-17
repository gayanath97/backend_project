package com.ems.entity;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Opd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String particulars;

    private Date date;

    private String sta_tus;

//    @OneToMany
//    private Set<OpdBill> opdBills;

//    @Enumerated
//    private CommonStatus statuss;

//    @OneToMany
//    private Set<EmployeeOpd> employeeOpds;
}