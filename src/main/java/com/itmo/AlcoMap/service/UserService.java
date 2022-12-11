package com.itmo.AlcoMap.service;

import com.itmo.AlcoMap.entity.User;
import com.itmo.AlcoMap.exception.UserAlreadyExistsException;
import com.itmo.AlcoMap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = getByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
