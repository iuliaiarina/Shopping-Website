package com.hellokoding.tutorials.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.*;
@Entity
public class Comanda implements Serializable {
    @Id
    private Long id;
    @OneToOne
    private User user;
    private Double pret;
    private Addresa adresa;


    public Comanda(Long id, User user, Double pret, Addresa adresa) {
        this.id = id;
        this.user = user;
        this.pret = pret;
        this.adresa = adresa;
    }

    public Comanda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Addresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Addresa adresa) {
        this.adresa = adresa;
    }
}
