package com.achmadqomarudin.animatedbottombar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.fragments.Ahorro;

import java.util.List;

public class adapterAhorro extends BaseAdapter {
    public List<Ahorro> listaConsulta;
    public Context context;
    public TextView t1,t2,t3;

    public adapterAhorro(List<Ahorro> listaConsulta, Context context) {
        this.listaConsulta = listaConsulta;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaConsulta.size();
    }

    @Override
    public Object getItem(int position) {
        return listaConsulta.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ahorro template = listaConsulta.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.lista_ahorro, null);
        t1 = convertView.findViewById(R.id.textidAhorro);
        t2 = convertView.findViewById(R.id.textnombreAhorro);
        t3 = convertView.findViewById(R.id.cantidad_abono_inicial);

        t1.setText(String.valueOf(template.getIdAhorro()));
        t2.setText(String.valueOf(template.getNombreAhorro()));
        t3.setText(String.valueOf(template.getAbono_inicial()));

        return convertView;
    }
}
