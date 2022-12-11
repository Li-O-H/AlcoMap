package com.itmo.AlcoMap.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
@Accessors(chain = true)
public class User {


//    @Column(name = "user_id")
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private UUID id;

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = Bar.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "bar_likes",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "bar_id"))
    private List<Bar> likedBars;

}
