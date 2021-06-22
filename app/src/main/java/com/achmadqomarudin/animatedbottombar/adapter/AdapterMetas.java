package com.achmadqomarudin.animatedbottombar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.models.MetaDeAhorro;

import java.util.ArrayList;

public class AdapterMetas extends BaseAdapter {

    private ArrayList<MetaDeAhorro> lista;
    private Context context;

    private TextView lblFechaMeta;
    private TextView lblCantMeta;

    public AdapterMetas(ArrayList<MetaDeAhorro> lista, Context context) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.lista_metas, parent, false);

        MetaDeAhorro metaDeAhorro = (MetaDeAhorro) getItem(position);

        lblFechaMeta = (TextView) convertView.findViewById(R.id.lblFechaMeta);
        lblCantMeta = (TextView) convertView.findViewById(R.id.lblCantMeta);

        lblFechaMeta.setText(metaDeAhorro.getFechaPropuesta());
        lblCantMeta.setText("$ " + String.valueOf(metaDeAhorro.getCantAhorrar()));

        return convertView;
    }
}
