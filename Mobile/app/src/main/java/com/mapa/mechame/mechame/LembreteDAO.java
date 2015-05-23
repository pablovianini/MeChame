package com.mapa.mechame.mechame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 11/05/2015.
 */
public class LembreteDAO {
    private SQLiteDatabase bd;

 public LembreteDAO(Context ctx){
        BancoDeDados auxBd = new BancoDeDados(ctx);
        bd=auxBd.getWritableDatabase();
    }

    public void inserir(Lembrete lembrete){
        ContentValues valores = new ContentValues();
        valores.put("titulo",lembrete.getTitulo());
        valores.put("raio",lembrete.getRaio());
        valores.put("latitude",lembrete.getLatitude());
        valores.put("longitude",lembrete.getLongitude());
        bd.insert("Lembretes",null,valores);
        Log.i("BANCO "," --> INSERIR LEMBRETES "+lembrete.getId()+" Raio "+lembrete.getRaio());
        bd.close();

    }


    public void lembreteToJson(String titulo, int raio, boolean ativo, double lat, double longi){

        Lembrete lembrete = new Lembrete();
        Gson gson = new Gson();
        String json;
        lembrete.setTitulo(titulo);
        lembrete.setRaio(raio);
        lembrete.setLatitude(lat);
        lembrete.setLongitude(longi);
        json = gson.toJson(lembrete);
        Log.d("Json", " Lembrete Completo -> " + json);
        bd.close();

    }

    public void editarLembrete(Lembrete lembrete){
        Log.d("Editar", lembrete.getLatitude()+" "+lembrete.getLongitude()+" "+lembrete.getTitulo());
        String sql = " UPDATE Lembretes SET titulo = '"+lembrete.getTitulo()+"', raio = "+lembrete.getRaio()+" WHERE latitude = "+lembrete.getLatitude()+" AND longitude = "+lembrete.getLongitude()+";";
        bd.execSQL(sql);
        bd.close();

        //bd.update("Lembretes",null, "latitude  = ? and longitude = ?", argumentos);

    }


    public List<Lembrete> listaLembretes() {
        List<Lembrete>lembretes= new ArrayList<Lembrete>();
        String sql = "SELECT * FROM Lembretes";
        Cursor c = bd.rawQuery(sql, null);
        while (c.moveToNext()){
            Lembrete lembrete = new Lembrete();
            lembrete.setTitulo(c.getString(c.getColumnIndex("titulo")));
            lembrete.setRaio(c.getInt(c.getColumnIndex("raio")));
            lembrete.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            lembrete.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
            lembretes.add(lembrete);
        }
        bd.close();
        return lembretes;
    }



    public void excluir (Lembrete lembrete){
        String sql = "DELETE FROM Lembretes WHERE titulo = '"+lembrete.getTitulo()+"'";
        bd.execSQL(sql);
       // String[] titulo ={lembrete.getTitulo().toString()};
        //bd.delete("Lembretes"," titulo =? ", titulo);
        bd.close();

    }

}
