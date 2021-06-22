package com.achmadqomarudin.animatedbottombar.models;

public class Transaccion {

    public int idTransaccion;
    public String tipo;
    public String fecha;
    public String cuenta;
    public String categoria;
    public Double monto;
    public String nota;

    public Transaccion(int idTransaccion, String tipo, String fecha, String cuenta, String categoria, Double monto, String nota ){
        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.categoria = categoria;
        this.monto = monto;
        this.nota = nota;
    }

}
