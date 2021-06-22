package com.achmadqomarudin.animatedbottombar;

public class AbonoAhorro {
    private int idAbonoAhorro;
    private int idAhorro;
    private int cantidad_abono;
    //private String fecha_Abono;

    public AbonoAhorro() {

    }

    public AbonoAhorro(int idAbonoAhorro, int idAhorro, int cantidad_abono) {
        this.idAbonoAhorro = idAbonoAhorro;
        this.idAhorro = idAhorro;
        this.cantidad_abono = cantidad_abono;
        //this.fecha_Abono = fecha_Abono;
    }

    public int getIdAbonoAhorro() {
        return idAbonoAhorro;
    }

    public void setIdAbonoAhorro(int idAbonoAhorro) {
        this.idAbonoAhorro = idAbonoAhorro;
    }

    public int getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(int idAhorro) {
        this.idAhorro = idAhorro;
    }

    public int getCantidad_abono() {
        return cantidad_abono;
    }

    public void setCantidad_abono(int cantidad_abono) {
        this.cantidad_abono = cantidad_abono;
    }
}
