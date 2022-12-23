package com.itmo.AlcoMap.repository;

import com.itmo.AlcoMap.entity.Bar;
import com.itmo.AlcoMap.entity.BarId;
import com.itmo.AlcoMap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarRepository extends CrudRepository<Bar, BarId> {

    List<Bar> findAllByLikes(User user);
}
