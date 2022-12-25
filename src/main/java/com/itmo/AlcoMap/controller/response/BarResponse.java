package com.itmo.AlcoMap.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BarResponse {
    private String name;
    private Float latitude;
    private Float longitude;
    private String address;
}
