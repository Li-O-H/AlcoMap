package com.itmo.AlcoMap.controller;

import com.itmo.AlcoMap.controller.response.MessageResponse;
import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.service.BarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bar")
@AllArgsConstructor
public class BarController {

    private final BarService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBar(@PathVariable int id) {
        Bar bar = service.getById(id);
        if (bar != null) {
            return ResponseEntity.ok(bar);
        }
        return new ResponseEntity<>("No such bar", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllBars() {
        return ResponseEntity.ok(service.getAllBars());
    }

    @GetMapping(params = "login")
    public ResponseEntity<?> getLikedBarsByUser(@RequestParam(value = "login") String login) {
        List<Bar> list = service.getLikedBarsByUser(login);
        return ResponseEntity.ok(list);
    }

    @PostMapping(params = {"login", "barId"})
    public ResponseEntity<?> addLike(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "barId") int barId) {
        service.addLike(login, barId);
        return ResponseEntity.ok(new MessageResponse("Like successfully added"));
    }

    @DeleteMapping(params = {"login", "barId"})
    public ResponseEntity<?> deleteLike(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "barId") int barId) {
        service.deleteLike(login, barId);
        return ResponseEntity.ok(new MessageResponse("Like successfully deleted"));
    }
}
