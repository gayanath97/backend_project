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
public class OpdBillDTO {


    private String id;
    private String amount;
    private String particulars;
    private String date;
    private String sta_tus;

//    private String opd;
//
//
//    private CommonStatus statuss;

}
