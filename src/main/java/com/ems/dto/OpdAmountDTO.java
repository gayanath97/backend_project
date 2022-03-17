package com.ems.dto;

import com.ems.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OpdAmountDTO {

    private String id;
    private String amount;
    private String expireDate;
    private String employee;

    private CommonStatus statuss;


}
