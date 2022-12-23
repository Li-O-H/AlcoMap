package com.itmo.AlcoMap.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@Entity(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = Bar.class, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = {
                    @JoinColumn(name = "name", referencedColumnName = "name"),
                    @JoinColumn(name = "latitude", referencedColumnName = "latitude"),
                    @JoinColumn(name = "longitude", referencedColumnName = "longitude")
            },
            name = "bar_likes")
    private List<Bar> likedBars = Collections.emptyList();

}
