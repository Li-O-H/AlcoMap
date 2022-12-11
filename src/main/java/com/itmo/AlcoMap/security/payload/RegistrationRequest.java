package com.itmo.AlcoMap.security.payload;

import com.itmo.AlcoMap.validation.PasswordMatching;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@PasswordMatching
public class RegistrationRequest {

    @NotEmpty(message = "Логин должен быть задан")
    private String login;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String password;

    @NotEmpty(message = "Пароль должен быть задан")
    @Size(min = 8, message = "Пароль не может быть короче 8 символов")
    private String matchingPassword;
}
