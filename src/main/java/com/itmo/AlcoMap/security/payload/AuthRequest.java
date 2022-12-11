package com.itmo.AlcoMap.security.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AuthRequest {

    @NotEmpty(message = "Логин должен быть задан")
    private String login;

    @NotEmpty(message = "Логин должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String password;
}
