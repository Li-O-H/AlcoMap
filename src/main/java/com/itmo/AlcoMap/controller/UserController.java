package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.entity.User;
import com.itmo.AlcoMap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{login}")
    public ResponseEntity<?> getUser(@PathVariable String login) {
        User user = service.getByLogin(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
    }
}
