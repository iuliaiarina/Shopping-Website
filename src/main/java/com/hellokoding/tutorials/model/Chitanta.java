package com.hellokoding.tutorials.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "chitanta")
public class Chitanta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TimpLivrare timpLivrare;

    public Chitanta(Long id, TimpLivrare timpLivrare) {
        this.id = id;
        this.timpLivrare = timpLivrare;
    }

    public Chitanta() {

    }
}
