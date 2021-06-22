package com.achmadqomarudin.animatedbottombar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbTransacciones extends ConnectionHelper {

    Context context;


    public dbTransacciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarTransaccion(String tipoTran, String fechaTran, String cuenta, String categoria, Double importeTran, String notaTran){

        long id = 0;

        try{

            ConnectionHelper connHelper = new ConnectionHelper(context);
            SQLiteDatabase db = connHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("tipoTran",tipoTran);
            values.put("fechaTran",fechaTran);
            values.put("cuentaTran",cuenta);
            values.put("categoriaTran",categoria);
            values.put("importeTran",importeTran);
            values.put("notaTran",notaTran);

            id = db.insert(TABLA_TRANSACCIONES, null, values);
            db.close();
        }
        catch (Exception e){
            e.toString();
        }

        return id;
    }

}