package com.itmo.AlcoMap.service;

import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.entity.User;
import com.itmo.AlcoMap.repository.BarRepository;
import com.itmo.AlcoMap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BarService {

    private final UserService userService;

    private final BarRepository repository;
    private final UserRepository userRepository;

    public Bar getById(int id) {
        Optional<Bar> bar = repository.findById(id);
        if (bar.isEmpty()) {
            return null;
        }
        return bar.get();
    }

    public List<Bar> getAllBars() {
        return (List<Bar>) repository.findAll();
    }

    public List<Bar> getLikedBarsByUser(String login) {
        User user = userService.getByLogin(login);
        if (user == null)
            return Collections.emptyList();
        List<Bar> result = repository.findAllByLikes(user);
        if (result == null)
            return Collections.emptyList();
        return result;
    }

    public List<User> getUsersByBar(int barId) {
        Bar bar = getById(barId);
        if (bar == null)
            return Collections.emptyList();
        List<User> result = userRepository.findAllByLikedBars(bar);
        if (result == null)
            return Collections.emptyList();
        return result;
    }

    public void addLike(String login, int id) {
        User user = userService.getByLogin(login);
        Optional<Bar> bar = repository.findById(id);
        if (bar.isEmpty() || user == null || getLikedBarsByUser(login).contains(bar.get()))
            return;
        bar.get().getLikes()
                .addAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.getByLogin(u.getLogin());
                            uu.getLikedBars().add(bar.get());
                            return uu;
                        }).collect(Collectors.toList()));
        repository.save(bar.get());
    }

    public void deleteLike(String login, int id) {
        User user = userService.getByLogin(login);
        Optional<Bar> bar = repository.findById(id);
        if (bar.isEmpty() || user == null)
            return;
        bar.get().getLikes()
                .addAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.getByLogin(u.getLogin());
                            uu.getLikedBars().remove(bar.get());
                            return uu;
                        }).collect(Collectors.toList()));
        repository.save(bar.get());
    }
}
