package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.controller.response.BarResponse;
import com.itmo.AlcoMap.controller.response.ObjectResponse;
import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.entity.BarId;
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
        List<BarResponse> list = service.getLikedBarsByUser(login);
        return ResponseEntity.ok(new ObjectResponse(list));
    }

    @PostMapping(params = {"login", "name", "latitude", "longitude", "address"})
    public ResponseEntity<?> addLike(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "latitude") Float latitude,
                                     @RequestParam(value = "longitude") Float longitude,
                                     @RequestParam(value = "address") String address) {
        Boolean success = service.addLike(login, name, latitude, longitude, address);
        if (success == null) {
            return ResponseEntity.ok(new ObjectResponse("Бар или пользователь не найден"));
        } else {
            if (success) {
                return ResponseEntity.ok(new ObjectResponse("Лайк успешно поставлен"));
            } else {
                return ResponseEntity.ok(new ObjectResponse("Лайк успешно убран"));
            }
        }
    }
}
