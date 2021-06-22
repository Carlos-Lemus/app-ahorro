package com.achmadqomarudin.animatedbottombar.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapter.AdapterAbonoMetas;
import com.achmadqomarudin.animatedbottombar.adapter.AdapterMetas;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;
import com.achmadqomarudin.animatedbottombar.models.AbonoMetaDelAhorro;
import com.achmadqomarudin.animatedbottombar.models.MetaDeAhorro;

import java.util.ArrayList;

public class AbonoFragment extends Fragment {

    private int id;
    private Button agregarAbonobtn;
    private EditText txtCantidadAbono;

    private AdapterAbonoMetas adapterAbonoMetas;
    private ListView listView;

    private ConnectionHelper dbHelper;
    private SQLiteDatabase db;

    public AbonoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_abono, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {

            return view;
        }

        this.id = bundle.getInt("idMeta");

        txtCantidadAbono = (EditText) view.findViewById(R.id.monto_meta_txt);
        listView = (ListView) view.findViewById(R.id.listViewAbonos);
        llenarListView();

        agregarAbonobtn = (Button) view.findViewById(R.id.ahorrar_meta_btn);

        agregarAbonobtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String cantidadText = txtCantidadAbono.getText().toString();

                if(cantidadText.isEmpty()) {
                    Toast.makeText(getContext(), "Debes ingresar un saldo", Toast.LENGTH_SHORT).show();

                    return;
                }

                dbHelper = new ConnectionHelper(getContext());

                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("idMetaAhorro", id);
                values.put("cantidad_abono", Double.parseDouble(cantidadText));
                db.insert(ConnectionHelper.TABLA_ABONOS_METAS, null, values);

                db.close();

                txtCantidadAbono.setText("");

                Toast.makeText(getContext(), "Estas cada vez mas cerca de la meta!", Toast.LENGTH_SHORT).show();

                llenarListView();
            }
        });

        return view;
    }

    private void llenarListView() {

        ArrayList<AbonoMetaDelAhorro> lista = new ArrayList<AbonoMetaDelAhorro>();

        dbHelper = new ConnectionHelper(getContext());

        db = dbHelper.getWritableDatabase();

        String sql = "SELECT idAbonoMetaAhorro," + ConnectionHelper.TABLA_ABONOS_METAS + ".idMetaAhorro, cantidad_abono, nombre_meta FROM " + ConnectionHelper.TABLA_ABONOS_METAS;
        sql += " INNER JOIN " + ConnectionHelper.TABLA_METAS + " ON " + ConnectionHelper.TABLA_ABONOS_METAS + ".idMetaAhorro = " + ConnectionHelper.TABLA_METAS+".idMetaAhorro";
        sql += " WHERE " + ConnectionHelper.TABLA_ABONOS_METAS + ".idMetaAhorro = " + id;

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()) {

            int idAbono = cursor.getInt(cursor.getColumnIndex("idAbonoMetaAhorro"));
            int idMeta = cursor.getInt(cursor.getColumnIndex("idMetaAhorro"));
            String nombre = cursor.getString(cursor.getColumnIndex("nombre_meta"));
            double cantidad = Double.parseDouble(cursor.getString(cursor.getColumnIndex("cantidad_abono")));

            AbonoMetaDelAhorro abonoMetaDelAhorro = new AbonoMetaDelAhorro(idAbono, idMeta, cantidad, nombre);;

            lista.add(abonoMetaDelAhorro);
        }

        cursor.close();

        adapterAbonoMetas = new AdapterAbonoMetas(lista, getContext());

        listView.setAdapter(adapterAbonoMetas);
    }

}