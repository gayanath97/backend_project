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
public class RewardDTO {

    private String rewardId;
    private String expireDate;
    private String amount;
    private String addedDate;
    private String employee;


    private CommonStatus statuss;
}
