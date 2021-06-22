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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.adapterAhorro;
import com.achmadqomarudin.animatedbottombar.database.ConnectionHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AhorrosFragment extends Fragment {
    Dialog ahorro, montoAhorro;
    FragmentManager fragmentManager;
    String nameAhorro;
    int cant_ahorro;
    Bundle datosAEnviar;
    public ArrayList<Ahorro> listaTarea;
    ListView lista;

    public AhorrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;

        view = inflater.inflate(R.layout.fragment_ahorros, container, false);
         datosAEnviar = new Bundle();
         mostrar(view);


        //**************** Listener para los botones de ahorros, esto es solo para la navegacion*******************
        Button ahorros = (Button) view.findViewById(R.id.ahorro_sin_cantidad_btn);
        Button meta = (Button) view.findViewById(R.id.meta_de_ahorro_btn);
        TextView da = (TextView) view.findViewById(R.id.datos);

        lista = (ListView)view.findViewById(R.id.listaAhorros);
        listaTarea = new ArrayList<Ahorro>();

        ahorro = new Dialog(getContext());
        ahorro.setContentView(R.layout.nuevo_ahorro);

        montoAhorro = new Dialog(getContext());
        montoAhorro.setContentView(R.layout.monto_ahorro);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ahorro ahorro = (Ahorro) lista.getAdapter().getItem(position);
                da.setText(String.valueOf(ahorro.getIdAhorro()));
                datosAEnviar.putInt("idAhorro", ahorro.getIdAhorro());
                datosAEnviar.putInt("PrimerAbono", ahorro.getAbono_inicial());
                datosAEnviar.putString("NombreAhorro", ahorro.getNombreAhorro());

                Fragment fragment = new AhorroFragment();
                fragment.setArguments(datosAEnviar);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });


        ahorros.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ahorro.show();
                EditText nombreAhorro = (EditText)ahorro.findViewById(R.id.nombre_de_ahorro);
                Button btnGuardarAhorro = (Button)ahorro.findViewById(R.id.btnAddAbono);

                btnGuardarAhorro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameAhorro = nombreAhorro.getText().toString();
                        datosAEnviar.putString("NombreAhorro", nameAhorro);
                        //Toast.makeText(getContext(), nombreAhorro.getText(), Toast.LENGTH_SHORT).show();
                        //datosAEnviar.putString("nombreAhorro", );
                        ahorro.dismiss();
                        montoAhorro.show();

                        EditText primerAbono = (EditText)montoAhorro.findViewById(R.id.PrimerAbono);
                        Button btnGuardarCant = (Button)montoAhorro.findViewById(R.id.btnAdd);

                        btnGuardarCant.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cant_ahorro = Integer.parseInt(primerAbono.getText().toString());
                                datosAEnviar.putInt("PrimerAbono", cant_ahorro);
                                montoAhorro.dismiss();

                                if(guardar(nameAhorro, cant_ahorro)) {
                                    Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "No guardado", Toast.LENGTH_SHORT).show();
                                }

                                //da.setText(String.valueOf(obtenerAhorro(nameAhorro)));

                                mostrar(view);
                            }
                        });
                    }
                });
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

    public Boolean guardar(String nameAhorro, int cant_ahorro) {
        ConnectionHelper objBase = new ConnectionHelper(getContext());
        SQLiteDatabase con = objBase.getWritableDatabase();

        try {
            ContentValues registro = new ContentValues();
            registro.put("nombreAhorro", nameAhorro);
            registro.put("montoAhorro", cant_ahorro);
            con.insert("tblAhorro", null, registro);
            //Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
            con.close();
            return true;

        }catch (Exception e) {
            //Toast.makeText(getContext(), "Ha ocurrido un error" + e.getMessage(), Toast.LENGTH_SHORT);
            return false;
        }
    }

    public void mostrar(View view) {
        listaTarea = new ArrayList<Ahorro>();
        lista = view.findViewById(R.id.listaAhorros);
        ConnectionHelper objBase = new ConnectionHelper(getContext());
        SQLiteDatabase con = objBase.getWritableDatabase();

        Cursor cursor = con.rawQuery("SELECT * FROM tblAhorro", null);

        while (cursor.moveToNext()) {
            listaTarea.add(new Ahorro(
                    cursor.getInt(cursor.getColumnIndex("idAhorro")),
                    cursor.getString(cursor.getColumnIndex("nombreAhorro")),
                    cursor.getInt(cursor.getColumnIndex("montoAhorro"))
            ));
        }

        adapterAhorro adapter = new adapterAhorro(listaTarea, getContext());
        lista.setAdapter(adapter);
    }
}


