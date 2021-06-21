package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.achmadqomarudin.animatedbottombar.R;


public class MetasFragment extends Fragment {

    FragmentManager fragmentManager;
    EditText fecha;
    public MetasFragment() {
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
        View view =inflater.inflate(R.layout.fragment_metas, container, false);


        fecha = (EditText) view.findViewById(R.id.fechapropuesta_txt);
        //********* Aqui pones el codigo para las metas ****************************

        //************************ SELECTOR DE FECHA********************************

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View modalAdd) {

                switch (modalAdd.getId()) {
                    case R.id.fechapropuesta_txt:
                        showDatePickerDialog();
                        break;

                }
            }
        });

        //******************* Para regresar a los dos botones******************************
        ImageButton regresar = (ImageButton) view.findViewById(R.id.regresar2_btn);
        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AhorrosFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });


        return view;

    }

    //******************************* METODO PARA EL SELECTOR DE FECHA ********************************************
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

}