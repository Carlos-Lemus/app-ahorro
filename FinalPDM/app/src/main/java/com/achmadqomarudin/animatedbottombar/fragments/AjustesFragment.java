package com.achmadqomarudin.animatedbottombar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.achmadqomarudin.animatedbottombar.R;


public class AjustesFragment extends Fragment {

    FragmentManager fragmentManager;
    EditText clave;
    Button guardar_clave;
    ImageButton regresar;
    boolean estado;
    public AjustesFragment() {
        // Required empty public constructor
    }
/*
    public AjustesFragment(boolean estado){
        this.estado = estado;
    }
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);


        this.estado = true;

        //******************* Regresar ***********************//
        regresar = (ImageButton) view.findViewById(R.id.regresar3_btn3);
        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new InicioFragment();

                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}