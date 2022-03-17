package com.ems.entity;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    @Column
    private Date expireDate;

    @Column
    private double amount;

    @Column
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "employee_employee_code")
    private Employee employee;

    @Enumerated
    private CommonStatus statuss;


}
