package com.ems.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonResponse {


    private List<Object> payload = null;
    private List<String> errorMessages = new ArrayList<>();
    private boolean status = false;


}
