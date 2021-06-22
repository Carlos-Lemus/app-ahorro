package com.achmadqomarudin.animatedbottombar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.fragments.Ahorro;

import java.util.List;

public class adapterAbonoAhorro extends BaseAdapter {
    public List<AbonoAhorro> listaConsulta;
    public Context context;
    public TextView t1,t2,t3;

    public adapterAbonoAhorro(List<AbonoAhorro> listaConsulta, Context context) {
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
        AbonoAhorro template = listaConsulta.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.list_abono_ahorro, null);
        t1 = convertView.findViewById(R.id.textfecha_deposito);
        t2 = convertView.findViewById(R.id.textmonto_deposito);

        t1.setText(String.valueOf(template.getIdAbonoAhorro()));
        t2.setText(String.valueOf(template.getCantidad_abono()));

        return convertView;
    }
}
