package com.achmadqomarudin.animatedbottombar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "BD";
    public static final String TABLA_TRANSACCIONES = "tablaTransacciones";
    public static final String TABLA_CUENTA = "tablaCuenta";
    public static final String TABLA_CATEGORIA = "tablaCategoria";
    public static final String TABLA_METAS = "tablaMetasDeAhorro";
    public static final String TABLA_ABONOS_METAS = "tablaAbonosMetas";
    public static final String TABLA_AHORRO = "tblAhorro";
    public static final String TABLA_ABONO_AHORRO = "tblAbonoAhorro";


    public ConnectionHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_TRANSACCIONES + "(" +
                "idTran INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipoTran TEXT NOT NULL," +
                "fechaTran DATE NOT NULL," +
                "cuentaTran TEXT NOT NULL," +
                "categoriaTran TEXT NOT NULL," +
                "importeTran REAL NOT NULL," +
                "notaTran TEXT NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_METAS+"("+
                "idMetaAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre_meta TEXT NOT NULL," +
                "fecha_propuesta TEXT NOT NULL," +
                "cant_ahorrar REAL NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE "+TABLA_ABONOS_METAS+"("+
                "idAbonoMetaAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idMetaAhorro TEXT NOT NULL," +
                "cantidad_abono TEXT NOT NULL," +
                "FOREIGN KEY('idMetaAhorro') REFERENCES " + TABLA_METAS + " ('idMetaAhorro') ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        db.execSQL("CREATE TABLE "+TABLA_AHORRO+"("+
                "idAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombreAhorro VARCHAR(50) NOT NULL UNIQUE,"+
                "montoAhorro INTEGER NOT NULL)");


        db.execSQL("CREATE TABLE "+TABLA_ABONO_AHORRO+"("+
                "idAbonoAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cantidad_abono INTEGER NOT NULL," +
                "idAhorro INTEGER NOT NULL CONSTRAINT fk_id_ahorro REFERENCES " + TABLA_AHORRO + "(idAhorro) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}