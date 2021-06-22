package com.achmadqomarudin.animatedbottombar.fragments;

public class Ahorro {
    private int idAhorro;
    private String nombreAhorro;
    private int abono_inicial;

    public Ahorro() {

    }

    public Ahorro(int idAhorro, String nombreAhorro, int abono_inicial) {
        this.idAhorro = idAhorro;
        this.nombreAhorro = nombreAhorro;
        this.abono_inicial = abono_inicial;
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

    public int getAbono_inicial() {
        return abono_inicial;
    }

    public void setAbono_inicial(int abono_inicial) {
        this.abono_inicial = abono_inicial;
    }
}
