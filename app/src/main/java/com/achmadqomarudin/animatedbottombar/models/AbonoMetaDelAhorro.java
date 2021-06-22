package com.achmadqomarudin.animatedbottombar.models;

public class AbonoMetaDelAhorro {

    private int idAbonoMetaDelAhorro;
    private int idMetaAhorro;
    private double cantidadAbono;
    private String nombreMeta;

    public AbonoMetaDelAhorro() {
    }

    public AbonoMetaDelAhorro(int idAbonoMetaDelAhorro, int idMetaAhorro, double cantidadAbono) {
        this.idAbonoMetaDelAhorro = idAbonoMetaDelAhorro;
        this.idMetaAhorro = idMetaAhorro;
        this.cantidadAbono = cantidadAbono;
    }

    public AbonoMetaDelAhorro(int idAbonoMetaDelAhorro, int idMetaAhorro, double cantidadAbono, String nombreMeta) {
        this.idAbonoMetaDelAhorro = idAbonoMetaDelAhorro;
        this.idMetaAhorro = idMetaAhorro;
        this.cantidadAbono = cantidadAbono;
        this.nombreMeta = nombreMeta;
    }

    public int getIdAbonoMetaDelAhorro() {
        return idAbonoMetaDelAhorro;
    }

    public void setIdAbonoMetaDelAhorro(int idAbonoMetaDelAhorro) {
        this.idAbonoMetaDelAhorro = idAbonoMetaDelAhorro;
    }

    public int getIdMetaAhorro() {
        return idMetaAhorro;
    }

    public void setIdMetaAhorro(int idMetaAhorro) {
        this.idMetaAhorro = idMetaAhorro;
    }

    public double getCantidadAbono() {
        return cantidadAbono;
    }

    public void setCantidadAbono(double cantidadAbono) {
        this.cantidadAbono = cantidadAbono;
    }

    public String getNombreMeta() {
        return nombreMeta;
    }

    public void setNombreMeta(String nombreMeta) {
        this.nombreMeta = nombreMeta;
    }

}
