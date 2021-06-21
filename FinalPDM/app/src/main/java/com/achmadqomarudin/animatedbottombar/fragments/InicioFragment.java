package com.achmadqomarudin.animatedbottombar.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.achmadqomarudin.animatedbottombar.R;



public class InicioFragment extends Fragment {

    FragmentManager fragmentManager;
    ImageButton ajustes;
    ConstraintLayout mensaje;
    ImageView imagen ;
    ListView lista;

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

        lista = (ListView) view.findViewById(R.id.lista_registros1);

        //************ Para controlar la lista de registros usa esta variable ****************

        boolean vacio = true;


        if (vacio){

            lista.setVisibility(View.GONE);

        }else {

            mensaje.setVisibility(View.GONE);
            imagen.setVisibility(View.GONE);

            lista.setVisibility(View.VISIBLE);
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

}