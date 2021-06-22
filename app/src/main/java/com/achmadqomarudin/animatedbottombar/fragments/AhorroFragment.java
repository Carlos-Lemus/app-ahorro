package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.R;


public class AhorroFragment extends Fragment {

    FragmentManager fragmentManager;

    TextView fechaInicio, fechaUltima, entrante;
    EditText monto;
    Button ahorrar, SI_btn, NO_btn;
    ImageButton martillo;
    ImageView cerdo;
    public AhorroFragment() {
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
        View view =inflater.inflate(R.layout.fragment_ahorro, container, false);

        Dialog modalAdd2 = new Dialog(this.getContext());
        modalAdd2.setContentView(R.layout.confirmar_martillo);

        //*********** Aqui pones el codigo para validar e ingresar a la db****************
        //Declaro los elementos

        fechaInicio = (TextView) view.findViewById(R.id.fecha_inicio_meta);

        fechaUltima = (TextView) view.findViewById(R.id.fecha_ultima_ahorro);
        monto = (EditText) view.findViewById(R.id.txtMonto_ahorro);
        ahorrar = (Button)view.findViewById(R.id.ahorro_btn);
        martillo = (ImageButton) view.findViewById(R.id.martillo_btn);
        entrante = (TextView) view.findViewById(R.id.entrantes);

        cerdo = (ImageView) view.findViewById(R.id.ahorro_img);

        Bundle datosRecuperados = getArguments();

        entrante.setText(datosRecuperados.getString("NombreAhorro") + " - " + String.valueOf(datosRecuperados.getInt("PrimerAbono")));


        //************** Modal Martillo***************
        martillo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SI_btn = (Button) modalAdd2.findViewById(R.id.si_conf_btn);
                NO_btn = (Button) modalAdd2.findViewById(R.id.no_conf_btn);

                SI_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        cerdo.setImageResource(R.drawable.checked);
                        martillo.setVisibility(View.GONE);
                        modalAdd2.dismiss();
                    }
                });

                NO_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    modalAdd2.dismiss();
                    }
                });


                modalAdd2.show();
            }
        });

        ahorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //************ Resetea El cerdo y el Martillo**************//////////

                cerdo.setImageResource(R.drawable.img3);

                if(martillo.getVisibility() == View.GONE){
                    martillo.setVisibility(View.VISIBLE);
                }

            }
        });


        //************* Boton para regresar a los dos botones*************************
        ImageButton regresar = (ImageButton) view.findViewById(R.id.regresar1_btn);
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
}