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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}