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
    public static final String TABLA_AHORRO = "tblAhorro";
    public static final String TABLA_RETIRO_AHORRO = "tblRetiroAhorro";
    public static final String TABLA_ABONO_AHORRO = "tblAbonoAhorro";

    public ConnectionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLA_TRANSACCIONES+"("+
                "idTran INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tipoTran TEXT NOT NULL,"+
                "fechaTran DATE NOT NULL,"+
                "idCuenta INTEGER NOT NULL CONSTRAINT fk_id_cuenta REFERENCES " + TABLA_CUENTA + "  (idCuenta) ON DELETE CASCADE ON UPDATE CASCADE,"+"" +
                "idCategoria INTEGER NOT NULL CONSTRAINT fk_id_categoria REFERENCES " + TABLA_CATEGORIA + "  (idCategoria) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "importeTran REAL NOT NULL,"+
                "notaTran TEXT NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_CUENTA+"("+
                "idCuenta INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombreCuenta TEXT NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_CATEGORIA+"("+
                "idCategoria INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombreCategoria TEXT NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_AHORRO+"("+
                "idAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombreAhorro VARCHAR(50) NOT NULL UNIQUE,"+
                "montoAhorro INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_RETIRO_AHORRO+"("+
                "idRetiroAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idAhorro INTEGER,"+
                "cantidad_retiro REAL NOT NULL)");

        db.execSQL("CREATE TABLE "+TABLA_ABONO_AHORRO+"("+
                "idAbonoAhorro INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cantidad_abono REAL NOT NULL," +
                "idAhorro INTEGER NOT NULL CONSTRAINT fk_id_ahorro REFERENCES " + TABLA_AHORRO + "(idAhorro) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}