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
public class ExpenseBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer extensionNo;

    private String particulars;

    private Double amount;

    private  String sta_tus;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;


}
