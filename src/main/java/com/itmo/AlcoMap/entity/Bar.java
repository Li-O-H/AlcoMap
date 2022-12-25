package com.itmo.AlcoMap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Bar {

    @EmbeddedId
    private BarId barId;

    @JsonIgnore
    @ManyToMany(mappedBy = "likedBars")
    private List<User> likes = Collections.emptyList();

    private String address;

    public Bar(BarId barId, String address) {
        this.barId = barId;
        this.address = address;
    }
}
