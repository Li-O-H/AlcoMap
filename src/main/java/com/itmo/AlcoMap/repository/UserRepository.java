package com.itmo.AlcoMap.repository;

import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByLogin(String login);

    List<User> findAllByLikedBars(Bar bar);
}
