package com.achmadqomarudin.animatedbottombar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.models.AbonoMetaDelAhorro;
import com.achmadqomarudin.animatedbottombar.models.MetaDeAhorro;

import java.util.ArrayList;

public class AdapterAbonoMetas extends BaseAdapter {

    private ArrayList<AbonoMetaDelAhorro> lista;
    private Context context;

    private TextView lblCantidad;
    private TextView lblNombre;

    public AdapterAbonoMetas(ArrayList<AbonoMetaDelAhorro> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.lista_abonos_meta, parent, false);

        AbonoMetaDelAhorro abonoMetaDelAhorro = (AbonoMetaDelAhorro) getItem(position);

        lblCantidad = (TextView) convertView.findViewById(R.id.lbl_nombre_meta);
        lblNombre = (TextView) convertView.findViewById(R.id.lbl_cantidad_abono);

        lblCantidad.setText("$ " + String.valueOf(abonoMetaDelAhorro.getCantidadAbono()));
        lblNombre.setText(abonoMetaDelAhorro.getNombreMeta());

        return convertView;
    }
}
