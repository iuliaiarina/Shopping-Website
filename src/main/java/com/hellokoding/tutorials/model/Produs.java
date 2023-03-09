package com.hellokoding.tutorials.model;

import javax.persistence.*;

@Entity
@Table(name = "produs")
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nume;
    @Column(nullable=false)
    private Double pret;
    @Column(nullable=false)
    private String producator;
    @Column(nullable=false)
    private Integer cantitate;

    public Produs(){}

    public Produs(Long id, String nume, Double pret, String producator, Integer cantitate) {
        super();
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.producator = producator;
        this.cantitate = cantitate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }


    public Double getPret() {
        return pret;
    }



    public void setPret(Double pret) {
        this.pret = pret;
    }



    public Integer getCantitate() {
        return cantitate;
    }



    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }



    @Override
    public String toString() {
        return "Produs [nume=" + nume + ", pret=" + pret + ", producator=" + producator + ", cantitate=" + cantitate
                + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produs produs = (Produs) o;

        return id != null ? id.equals(produs.id) : produs.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
