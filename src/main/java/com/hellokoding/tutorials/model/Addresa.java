package com.hellokoding.tutorials.model;


import java.io.Serializable;

public class Addresa implements Serializable{
    public Addresa(String judet, String oras, String strada) {
        super();
        this.judet = judet;
        this.oras = oras;
        this.Strada = strada;

    }
    private String judet;
    private String oras;
    private String Strada;

    public String getJudet() {
        return judet;
    }
    public void setJudet(String judet) {
        this.judet = judet;
    }
    public String getOras() {
        return oras;
    }
    public void setOras(String oras) {
        this.oras = oras;
    }
    public String getStrada() {
        return Strada;
    }
    public void setStrada(String strada) {
        Strada = strada;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Strada == null) ? 0 : Strada.hashCode());
        result = prime * result + ((judet == null) ? 0 : judet.hashCode());
        result = prime * result + ((oras == null) ? 0 : oras.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Addresa other = (Addresa) obj;
        if (Strada == null) {
            if (other.Strada != null)
                return false;
        } else if (!Strada.equals(other.Strada))
            return false;
        if (judet == null) {
            if (other.judet != null)
                return false;
        } else if (!judet.equals(other.judet))
            return false;
        if (oras == null) {
            if (other.oras != null)
                return false;
        } else if (!oras.equals(other.oras))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Addresa [judet=" + judet + ", oras=" + oras + ", Strada=" + Strada + "]";
    }


}
