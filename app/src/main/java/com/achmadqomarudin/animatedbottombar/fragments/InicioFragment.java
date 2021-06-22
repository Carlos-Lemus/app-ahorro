package com.achmadqomarudin.animatedbottombar.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapter.AdapterMetas;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;
import com.achmadqomarudin.animatedbottombar.models.MetaDeAhorro;

import java.util.ArrayList;


public class InicioFragment extends Fragment {

    private FragmentManager fragmentManager;
    private ImageButton ajustes;
    private ConstraintLayout mensaje;
    private ImageView imagen ;
    private ListView listView;

    private ConnectionHelper dbHelper;
    private SQLiteDatabase db;

    private AdapterMetas adapterMetas;

    public InicioFragment() {
        // Required empty public constructor
    }


/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        mensaje = (ConstraintLayout) view.findViewById(R.id.mensaje_vacio);
        imagen = (ImageView) view.findViewById(R.id.imagen_vacio);

        listView = (ListView) view.findViewById(R.id.lista_registros1);

        //************ Para controlar la lista de registros usa esta variable ****************

        llenarListView();

        boolean vacio = listView.getAdapter().getCount() < 1;

        if (vacio){

            listView.setVisibility(View.GONE);

        }else {

            mensaje.setVisibility(View.GONE);
            imagen.setVisibility(View.GONE);

            listView.setVisibility(View.VISIBLE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    MetaDeAhorro metaDeAhorro = (MetaDeAhorro) listView.getAdapter().getItem(position);

                    Bundle bundle = new Bundle();
                    bundle.putInt("idMeta", metaDeAhorro.getIdMetaDeAhorro());

                    Fragment fragment = new AbonoFragment();
                    fragment.setArguments(bundle);

                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                            .commit();


                }
            });
        }

        //********** Abrir Ajustes **********************
        ajustes = (ImageButton) view.findViewById(R.id.ajustes_vacio);
        ajustes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Fragment fragment = new AjustesFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });


        return view;
    }

    private void llenarListView() {

        dbHelper = new ConnectionHelper(getContext());

        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConnectionHelper.TABLA_METAS, null);

        ArrayList<MetaDeAhorro> lista = new ArrayList<MetaDeAhorro>();

        while(cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex("idMetaAhorro"));
            String name = cursor.getString(cursor.getColumnIndex("nombre_meta"));
            double cantidad = Double.parseDouble(cursor.getString(cursor.getColumnIndex("cant_ahorrar")));
            String fecha = cursor.getString(cursor.getColumnIndex("fecha_propuesta"));

            MetaDeAhorro metaDeAhorro = new MetaDeAhorro(id, name, fecha, cantidad);;

            lista.add(metaDeAhorro);
        }

        cursor.close();

        adapterMetas = new AdapterMetas(lista, getContext());

        listView.setAdapter(adapterMetas);
    }

}