package com.achmadqomarudin.animatedbottombar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.models.Transaccion;

import java.util.ArrayList;

public class TransaccionesAdapter extends BaseAdapter {

    public ArrayList<Transaccion> dt;
    public Context context;

    public TextView tipo, fecha, cuenta, categoria, monto, nota;

    public TransaccionesAdapter(ArrayList<Transaccion> dt, Context context){
        this.dt = dt;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dt.size();
    }

    @Override
    public Object getItem(int position) {
        return dt.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaccion t = (Transaccion)getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.lista_transacciones, null);

        tipo = convertView.findViewById(R.id.tipoTxt);
        fecha = convertView.findViewById(R.id.fechaTxt);
        cuenta = convertView.findViewById(R.id.cuentaTxt);
        categoria = convertView.findViewById(R.id.categoriaTxt);
        monto = convertView.findViewById(R.id.montoTxt);
        nota = convertView.findViewById(R.id.notaTxt);

        tipo.setText(t.tipo);
        fecha.setText(t.fecha);
        cuenta.setText(t.cuenta);
        categoria.setText(t.categoria);
        monto.setText(t.monto.toString());
        nota.setText(t.nota);

        return convertView;
    }
}
