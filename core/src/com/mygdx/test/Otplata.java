package com.mygdx.test;

public class Otplata {

    private int razdoblje;
    private double anuitet;
    private double kamate;
    private double kvota;
    private double ostatakDuga;
    private double kamateTotal;

    public void setRazdoblje(int razdoblje) {
        this.razdoblje = razdoblje;
    }

    public int getRazdoblje() {
        return razdoblje;
    }

    public void setAnuitet(double anuitet) {
        this.anuitet = anuitet;
    }

    public double getAnuitet() {
        return anuitet;
    }

    public void setKamate(double kamate) {
        this.kamate = kamate;
    }

    public double getKamate() {
        return kamate;
    }

    public void setKvota(double kvota) {
        this.kvota = kvota;
    }

    public double getKvota() {
        return kvota;
    }

    public void setOstatakDuga(double ostatakDuga) {
        this.ostatakDuga = ostatakDuga;
    }

    public double getOstatakDuga() {
        return ostatakDuga;
    }

    public double getKamateTotal() {
        return kamateTotal;
    }

    public void setKamateTotal(double kamateTotal) {
        this.kamateTotal = kamateTotal;
    }
}
