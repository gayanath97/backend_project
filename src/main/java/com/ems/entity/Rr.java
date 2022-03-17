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
public class Rr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer extensionNo;
    private String customer;
    private String location;
    private String sta_tus;

    @OneToMany(mappedBy = "rr")
    private Set<RrBill> rrBills;


//    @OneToMany
//    private Set<EmployeeRr> employeeRrs;


}
