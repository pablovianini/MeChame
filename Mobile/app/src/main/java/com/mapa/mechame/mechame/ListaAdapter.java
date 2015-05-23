package com.mapa.mechame.mechame;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rafael on 22/05/2015.
 */
public class ListaAdapter extends BaseAdapter {

    private List<Lembrete> lembretes;
    private Activity activity;
    public ListaAdapter(List<Lembrete> lembretes, Activity activity) {
        this.lembretes = lembretes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lembretes.size();
    }

    @Override
    public Object getItem(int position) {
        return lembretes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lembretes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lembrete lembrete = lembretes.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
      //  View linha = inflater.inflate(R.layout.lista_lembrete,null);

       // TextView titulo = (TextView) linha.findViewById(R.id.listaNomeLembrete);
       // titulo.setText(lembrete.getTitulo().toString());



        return null;
    }
}
