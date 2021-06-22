package com.achmadqomarudin.animatedbottombar.fragments;

public class Ahorro {
    private int idAhorro;
    private String nombreAhorro;

    public Ahorro() {

    }

    public Ahorro(int idAhorro, String nombreAhorro) {
        this.idAhorro = idAhorro;
        this.nombreAhorro = nombreAhorro;
    }

    public int getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(int idAhorro) {
        this.idAhorro = idAhorro;
    }

    public String getNombreAhorro() {
        return nombreAhorro;
    }

    public void setNombreAhorro(String nombreAhorro) {
        this.nombreAhorro = nombreAhorro;
    }
}
