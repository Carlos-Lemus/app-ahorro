package com.achmadqomarudin.animatedbottombar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "BDControlAhorros";
    public static final String TABLA_TRANSACCIONES = "tablaTransacciones";
    public static final String TABLA_CUENTA = "tablaCuenta";
    public static final String TABLA_CATEGORIA = "tablaCategoria";


    public ConnectionHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_TRANSACCIONES + "(" +
                "idTran INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipoTran TEXT NOT NULL," +
                "fechaTran DATE NOT NULL," +
                "idCuenta INTEGER NOT NULL CONSTRAINT fk_id_cuenta REFERENCES " + TABLA_CUENTA + "  (idCuenta) ON DELETE CASCADE ON UPDATE CASCADE," + "" +
                "idCategoria INTEGER NOT NULL CONSTRAINT fk_id_categoria REFERENCES " + TABLA_CATEGORIA + "  (idCategoria) ON DELETE CASCADE ON UPDATE CASCADE," +
                "importeTran REAL NOT NULL," +
                "notaTran TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_CUENTA + "(" +
                "idCuenta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreCuenta TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_CATEGORIA + "(" +
                "idCategoria INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreCategoria TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}