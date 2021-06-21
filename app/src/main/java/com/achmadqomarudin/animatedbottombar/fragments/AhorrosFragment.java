package com.achmadqomarudin.animatedbottombar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.achmadqomarudin.animatedbottombar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AhorrosFragment extends Fragment {

    FragmentManager fragmentManager;

    public AhorrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;

        view = inflater.inflate(R.layout.fragment_ahorros, container, false);

        //**************** Listener para los botones de ahorros, esto es solo para la navegacion*******************
        Button ahorros = (Button) view.findViewById(R.id.ahorro_sin_cantidad_btn);
        Button meta = (Button) view.findViewById(R.id.meta_de_ahorro_btn);

        ahorros.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AhorroFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        meta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new MetasFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}
