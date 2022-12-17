package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.entity.User;
import com.itmo.AlcoMap.service.BarService;
import com.itmo.AlcoMap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final BarService barService;

    @GetMapping("/{login}")
    public ResponseEntity<?> getUser(@PathVariable String login) {
        User user = service.getByLogin(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "bar")
    public ResponseEntity<?> getUsersByBar(@RequestParam(value = "bar") int barId) {
        List<User> list = barService.getUsersByBar(barId);
        return ResponseEntity.ok(list);
    }
}