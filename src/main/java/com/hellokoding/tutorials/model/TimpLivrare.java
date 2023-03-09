package com.hellokoding.tutorials.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public class TimpLivrare implements Serializable{


    private int ore;

    public TimpLivrare(int ore) {
        this.ore = ore;
    }

    public java.util.Date getDateAfter (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, ore);
        return cal.getTime();
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    @Override
    public String toString() {
        return "timpLivrare [ore=" + ore + "]";
    }



}
