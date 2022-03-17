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
@Data
@Builder
@Entity
public class OpdBill {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double amount;

        private String particulars;

        private Date date;

        private String sta_tus;

//        @ManyToOne
//        @JoinColumn(name = "opd_id")
//        private Opd opd;
//
//        @Enumerated
//        private CommonStatus statuss;


}
