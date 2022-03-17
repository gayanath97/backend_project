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
public class ExpenseBillDTO {

    private String id;
    private String extensionNo;
    private String particulars;
    private String amount;
    private  String sta_tus;
    private String expense;


}
