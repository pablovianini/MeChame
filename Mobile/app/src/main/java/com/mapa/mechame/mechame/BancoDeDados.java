package com.mapa.mechame.mechame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rafael on 19/05/2015.
 */
public class BancoDeDados extends SQLiteOpenHelper {

    private static final String NOME_BD = "ChameBd";
    private static final int VERSAO_BD = 1;

    public BancoDeDados(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE Usuarios("+
                      "telefone VARCHAR(20),nome VARCHAR(30), email VARCHAR(30), senha VARCHAR(8), cadastrado BOOLEAN)";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE Lembretes (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                      "titulo VARCHAR(20), raio INTEGER, latitude FLOAT, longitude FLOAT); ";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
