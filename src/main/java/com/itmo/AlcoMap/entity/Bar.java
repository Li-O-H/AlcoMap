package com.itmo.AlcoMap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "site")
    private String site;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @ManyToMany(mappedBy = "likedBars")
    private List<User> likes;

    @Column(name = "monOpen")
    private LocalTime monOpen;

    @Column(name = "monClose")
    private LocalTime monClose;

    @Column(name = "tueOpen")
    private LocalTime tueOpen;

    @Column(name = "tueClose")
    private LocalTime tueClose;

    @Column(name = "wedOpen")
    private LocalTime wedOpen;

    @Column(name = "wedClose")
    private LocalTime wedClose;

    @Column(name = "thuOpen")
    private LocalTime thuOpen;

    @Column(name = "thuClose")
    private LocalTime thuClose;

    @Column(name = "friOpen")
    private LocalTime friOpen;

    @Column(name = "friClose")
    private LocalTime friClose;

    @Column(name = "satOpen")
    private LocalTime satOpen;

    @Column(name = "satClose")
    private LocalTime satClose;

    @Column(name = "sunOpen")
    private LocalTime sunOpen;

    @Column(name = "sunClose")
    private LocalTime sunClose;

}
