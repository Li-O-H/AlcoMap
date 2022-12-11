package com.itmo.AlcoMap.dto;

import com.itmo.AlcoMap.entity.Bar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {

//    private UUID id;
    private String login;
    private String password;
    private List<Bar> likedBars;
}
