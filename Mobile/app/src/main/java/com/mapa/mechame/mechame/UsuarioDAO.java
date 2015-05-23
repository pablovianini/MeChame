package com.mapa.mechame.mechame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Rafael on 10/05/2015.
 */
public class UsuarioDAO{

    private SQLiteDatabase bd;

    public UsuarioDAO(Context ctx){
        BancoDeDados auxBd = new BancoDeDados(ctx);
        bd=auxBd.getWritableDatabase();
    }



    public void usuarioToJson(String tel,String nome, String email, String senha){
        Usuario usuario = new Usuario();
        Gson gson = new Gson();
        String json;
        usuario.setTelefone(tel);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setEmail(email);

        json = gson.toJson(usuario);

        Log.d("Json"," Usuario Completo -> "+json);

    }


    public void inserir(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("telefone",usuario.getTelefone());
        valores.put("nome",usuario.getNome());
        valores.put("email",usuario.getEmail());
        valores.put("senha",usuario.getSenha());
        valores.put("cadastrado",usuario.isCadastrado());
        bd.insert("Usuarios",null,valores);

        Log.i("BANCO "," --> INSERIR USUARIOS "+usuario.getTelefone()+" "+usuario.getNome());
        bd.close();
    }

    public Usuario mostrarUsuario(){
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM Usuarios";
        Cursor c;
        c = bd.rawQuery(sql,null);
        usuario.setNome(c.getString(c.getColumnIndex("nome")));
        usuario.setEmail(c.getString(c.getColumnIndex("email")));
        return usuario;
    }

    public boolean login(){
        String sql = "SELECT * FROM Usuarios";
        Cursor c;
        c = bd.rawQuery(sql,null);
       // bd.close();
        return (c.getCount()>0);

    }


}
