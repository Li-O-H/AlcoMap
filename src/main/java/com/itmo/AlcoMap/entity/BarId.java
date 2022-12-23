package com.itmo.AlcoMap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BarId implements Serializable {

    private String name;
    private Float latitude;
    private Float longitude;
}
