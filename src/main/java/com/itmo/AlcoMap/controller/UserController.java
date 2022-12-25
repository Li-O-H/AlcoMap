package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.controller.response.MessageResponse;
import com.itmo.AlcoMap.entity.BarId;
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

    @GetMapping(params = {"name", "latitude", "longitude"})
    public ResponseEntity<?> getUsersByBar(@RequestParam(value = "name") String name,
                                           @RequestParam(value = "latitude") Float latitude,
                                           @RequestParam(value = "longitude") Float longitude) {
        List<User> list = barService.getUsersByBar(new BarId(name, latitude, longitude));
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = {"name", "latitude", "longitude"}, path = "/{login}/haslike")
    public ResponseEntity<?> hasUserLike(@PathVariable String login,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "latitude") Float latitude,
                                         @RequestParam(value = "longitude") Float longitude) {
        return ResponseEntity.ok(new MessageResponse(barService.hasUserLike(login, name, latitude, longitude).toString()));
    }
}
