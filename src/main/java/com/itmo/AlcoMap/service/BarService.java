package com.itmo.AlcoMap.service;

import com.itmo.AlcoMap.controller.response.BarResponse;
import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.entity.BarId;
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

    public Bar getById(BarId id) {
        Optional<Bar> bar = repository.findById(id);
        if (bar.isEmpty()) {
            return null;
        }
        return bar.get();
    }

    public List<BarResponse> getLikedBarsByUser(String login) {
        User user = userService.getByLogin(login);
        if (user == null)
            return Collections.emptyList();
        List<Bar> result = repository.findAllByLikes(user);
        if (result == null)
            return Collections.emptyList();
        return result.stream().map(bar -> new BarResponse(bar.getBarId().getName(),
                bar.getBarId().getLatitude(), bar.getBarId().getLongitude(), bar.getAddress()))
                .collect(Collectors.toList());
    }

    public List<User> getUsersByBar(BarId barId) {
        Bar bar = getById(barId);
        if (bar == null)
            return Collections.emptyList();
        List<User> result = userRepository.findAllByLikedBars(bar);
        if (result == null)
            return Collections.emptyList();
        return result;
    }

    public Boolean hasUserLike(String login, String name, Float latitude, Float longitude) {
        User user = userService.getByLogin(login);
        if (user == null)
            return false;
        List<Bar> result = repository.findAllByLikes(user);
        if (result == null)
            return false;
        return result.stream().map(Bar::getBarId).collect(Collectors.toList())
                .contains(new BarId(name, latitude, longitude));
    }

    public void addBar(String name, Float latitude, Float longitude, String address) {
        repository.save(new Bar(new BarId(name, latitude, longitude), address));
    }

    public void addLike(String login, String name, Float latitude, Float longitude, String address) {
        User user = userService.getByLogin(login);
        Bar bar = getById(new BarId(name, latitude, longitude));
        if (user == null)
            return;
        if (bar == null) {
            addBar(name, latitude, longitude, address);
            addLike(login, name, latitude, longitude, address);
            return;
        }
        if (bar.getLikes().contains(user)) {
            deleteLike(login, name, latitude, longitude);
            return;
        }
        bar.getLikes()
                .addAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.getByLogin(u.getLogin());
                            uu.getLikedBars().add(bar);
                            return uu;
                        }).collect(Collectors.toList()));
        repository.save(bar);
    }

    public void deleteLike(String login, String name, Float latitude, Float longitude) {
        User user = userService.getByLogin(login);
        Optional<Bar> bar = repository.findById(new BarId(name, latitude, longitude));
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
