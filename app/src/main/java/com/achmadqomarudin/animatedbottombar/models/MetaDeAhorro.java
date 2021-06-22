package com.achmadqomarudin.animatedbottombar.models;

public class MetaDeAhorro {

    private int idMetaDeAhorro;
    private String nombreMeta;
    private String fechaPropuesta;
    private double cantAhorrar;

    public MetaDeAhorro() {
    }

    public MetaDeAhorro(int idMetaDeAhorro, String nombreMeta, String fechaPropuesta, double cantAhorrar) {
        this.idMetaDeAhorro = idMetaDeAhorro;
        this.nombreMeta = nombreMeta;
        this.fechaPropuesta = fechaPropuesta;
        this.cantAhorrar = cantAhorrar;
    }

    public int getIdMetaDeAhorro() {
        return idMetaDeAhorro;
    }

    public void setIdMetaDeAhorro(int idMetaDeAhorro) {
        this.idMetaDeAhorro = idMetaDeAhorro;
    }

    public String getNombreMeta() {
        return nombreMeta;
    }

    public void setNombreMeta(String nombreMeta) {
        this.nombreMeta = nombreMeta;
    }

    public String getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(String fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public double getCantAhorrar() {
        return cantAhorrar;
    }

    public void setCantAhorrar(double cantAhorrar) {
        this.cantAhorrar = cantAhorrar;
    }
}
