package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.controller.response.TokenResponse;
import com.itmo.AlcoMap.entity.User;
import com.itmo.AlcoMap.exception.UserAlreadyExistsException;
import com.itmo.AlcoMap.security.jwt.JwtProvider;
import com.itmo.AlcoMap.security.payload.AuthRequest;
import com.itmo.AlcoMap.security.payload.RegistrationRequest;
import com.itmo.AlcoMap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid RegistrationRequest request) {
        User user = new User()
                .setLogin(request.getLogin())
                .setPassword(request.getPassword());
        try {
            userService.save(user);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return login(new AuthRequest(request.getLogin(), request.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        User user = userService.getByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
