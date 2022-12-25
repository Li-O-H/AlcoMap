package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.controller.response.ObjectResponse;
import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.service.BarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bar")
@AllArgsConstructor
public class BarController {

    private final BarService service;

    @GetMapping(params = "login")
    public ResponseEntity<?> getLikedBarsByUser(@RequestParam(value = "login") String login) {
        List<Bar> list = service.getLikedBarsByUser(login);
        return ResponseEntity.ok(new ObjectResponse(list));
    }

    @PostMapping(params = {"login", "name", "latitude", "longitude"})
    public ResponseEntity<?> addLike(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "latitude") Float latitude,
                                     @RequestParam(value = "longitude") Float longitude) {
        service.addLike(login, name, latitude, longitude);
        return ResponseEntity.ok(new ObjectResponse("Like successfully added/removed"));
    }
}
