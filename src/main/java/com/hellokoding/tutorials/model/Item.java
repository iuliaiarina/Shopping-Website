package com.hellokoding.tutorials.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private Long id;
    private String nume;
    private Double pret;
    private String producator;
    private Integer stoc;

    private Integer cantitate;

    public Item(Long id, String nume, Double pret, String producator, Integer stoc, Integer cantitate) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.producator = producator;
        this.stoc = stoc;
        this.cantitate = cantitate;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item() {}


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", producator='" + producator + '\'' +
                ", stoc=" + stoc +
                ", cantitate=" + cantitate +
                '}';
    }

}
