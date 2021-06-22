package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapter.AdapterMetas;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;
import com.achmadqomarudin.animatedbottombar.models.MetaDeAhorro;

import java.util.ArrayList;


public class MetasFragment extends Fragment {

    private FragmentManager fragmentManager;
    private EditText fecha;
    private Button agregarMetaBtn;

    private EditText txtNombreMeta;
    private EditText txtCantTotalMeta;
    private EditText txtFechaMeta;

    private ConnectionHelper dbHelper;
    private SQLiteDatabase db;

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

        agregarMetaBtn = (Button) view.findViewById(R.id.agregarmeta_btn);
        agregarMetaBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                txtNombreMeta = (EditText) view.findViewById(R.id.nombre_meta_txt);
                txtCantTotalMeta = (EditText) view.findViewById(R.id.csntidadtotal_meta_txt);
                txtFechaMeta = (EditText) view.findViewById(R.id.fechapropuesta_txt);

                String nombreMeta = txtNombreMeta.getText().toString();
                String cantTotal = txtCantTotalMeta.getText().toString();
                String fecha = txtFechaMeta.getText().toString();

                if(nombreMeta.isEmpty() || cantTotal.isEmpty() || fecha.isEmpty()) {
                    Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

                    return;
                }

                dbHelper = new ConnectionHelper(getContext());

                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("nombre_meta", nombreMeta);
                values.put("cant_ahorrar", Double.parseDouble(cantTotal));
                values.put("fecha_propuesta ", fecha);

                db.insert(ConnectionHelper.TABLA_METAS, null, values);

                db.close();

                txtNombreMeta.setText("");
                txtCantTotalMeta.setText("");

                Toast.makeText(getContext(), "Es hora de cumplir tu meta!!!", Toast.LENGTH_SHORT).show();
            }
        });

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