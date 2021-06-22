package com.achmadqomarudin.animatedbottombar.fragments;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.achmadqomarudin.animatedbottombar.AbonoAhorro;
import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapterAbonoAhorro;
import com.achmadqomarudin.animatedbottombar.adapterAhorro;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;

import java.util.ArrayList;


public class AhorroFragment extends Fragment {

    FragmentManager fragmentManager;

    TextView entrante;
    EditText monto;
    Button ahorrar, SI_btn, NO_btn;
    ImageButton martillo;
    ImageView cerdo;
    Bundle datosRecuperados;
    public ArrayList<AbonoAhorro> listaTarea;
    ListView lista;
    int acumulador;

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
        datosRecuperados = getArguments();

        monto = (EditText) view.findViewById(R.id.txtMonto_ahorro);
        ahorrar = (Button)view.findViewById(R.id.ahorro_btn);
        martillo = (ImageButton) view.findViewById(R.id.martillo_btn);
        entrante = (TextView) view.findViewById(R.id.entrantes);
        TextView saldo_ahorro = (TextView) view.findViewById(R.id.saldoAhorro);
        TextView number = (TextView) view.findViewById(R.id.numero);

        lista = (ListView)view.findViewById(R.id.listaAbonos_De_Ahorro);
        listaTarea = new ArrayList<AbonoAhorro>();

        cerdo = (ImageView) view.findViewById(R.id.ahorro_img);
        acumulador = datosRecuperados.getInt("PrimerAbono");

        mostrar(view);

        entrante.setText(String.valueOf(datosRecuperados.getInt("idAhorro") + datosRecuperados.getString("NombreAhorro") + " - " + String.valueOf(datosRecuperados.getInt("PrimerAbono"))));
        saldo_ahorro.setText("$"+String.valueOf(datosRecuperados.getInt("PrimerAbono")));

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
                number.setText(monto.getText().toString());
                if(guardar(datosRecuperados.getInt("idAhorro"), Integer.parseInt(monto.getText().toString()))) {
                    Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
                    int res  = update(Integer.parseInt(monto.getText().toString()));

                    saldo_ahorro.setText("$"+String.valueOf(res));
                } else {
                    Toast.makeText(getContext(), "No guardado", Toast.LENGTH_SHORT).show();
                }

                //************ Resetea El cerdo y el Martillo**************//////////

                cerdo.setImageResource(R.drawable.img3);

                if(martillo.getVisibility() == View.GONE){
                    martillo.setVisibility(View.VISIBLE);
                }
                mostrar(view);
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

    public Boolean guardar(int idAhorro, int cantidad) {
        ConnectionHelper objBase = new ConnectionHelper(getContext());
        SQLiteDatabase con = objBase.getWritableDatabase();

        try {
            ContentValues registro = new ContentValues();
            registro.put("cantidad_abono", cantidad);
            registro.put("idAhorro", idAhorro);
            con.insert("tblAbonoAhorro", null, registro);
            //Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
            con.close();
            return true;

        }catch (Exception e) {
            //Toast.makeText(getContext(), "Ha ocurrido un error" + e.getMessage(), Toast.LENGTH_SHORT);
            return false;
        }
    }

    public void mostrar(View view) {
        listaTarea = new ArrayList<AbonoAhorro>();
        lista = view.findViewById(R.id.listaAbonos_De_Ahorro);
        ConnectionHelper objBase = new ConnectionHelper(getContext());
        SQLiteDatabase con = objBase.getWritableDatabase();

        Cursor cursor = con.rawQuery("SELECT * FROM tblAbonoAhorro WHERE idAhorro="+datosRecuperados.getInt("idAhorro"), null);

        while (cursor.moveToNext()) {
            listaTarea.add(new AbonoAhorro(
                    cursor.getInt(cursor.getColumnIndex("idAbonoAhorro")),
                    cursor.getInt(cursor.getColumnIndex("idAhorro")),
                    cursor.getInt(cursor.getColumnIndex("cantidad_abono"))
            ));
        }

        adapterAbonoAhorro adapter = new adapterAbonoAhorro(listaTarea, getContext());
        lista.setAdapter(adapter);
    }

    public int update(int monto) {
        ConnectionHelper objBase = new ConnectionHelper(getContext());
        SQLiteDatabase con = objBase.getWritableDatabase();

        acumulador += monto;
        int res = acumulador;

        ContentValues registro = new ContentValues();
        registro.put("montoAhorro", acumulador );

        String selection = "idAhorro = ?";
        String[] selectionArgs = { String.valueOf(datosRecuperados.getInt("idAhorro")) };

        con.update("tblAhorro", registro, selection , selectionArgs);

        return res;
    }
}