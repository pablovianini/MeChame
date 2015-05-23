package com.mapa.mechame.mechame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class CadastroUsuario extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);

        final TextView txtNomeUser = (TextView) findViewById(R.id.txtConfigNomeUsuario);
        final TextView txtEmail = (TextView) findViewById(R.id.txtEmailUsuario);
        final TextView txtSenha = (TextView) findViewById(R.id.txtSenhaUsuario);



        ImageButton btnCadastrar = (ImageButton) findViewById(R.id.btnCadastraUsuario);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tel= (String) getIntent().getSerializableExtra("telefone");
                UsuarioDAO dao = new UsuarioDAO(CadastroUsuario.this);
                Usuario usuario = new Usuario();
                if(validacao()){
                    usuario.setTelefone(tel);
                    usuario.setNome(txtNomeUser.getText().toString());
                    usuario.setEmail(txtEmail.getText().toString());
                    usuario.setSenha(txtSenha.getText().toString());
                    usuario.setCadastrado(true);
                    dao.inserir(usuario);
                    dao.usuarioToJson(tel,txtNomeUser.getText().toString(),txtEmail.getText().toString(),txtSenha.getText().toString());
                    finish();
                    Intent irMapa = new Intent(CadastroUsuario.this,MapsActivity.class);
                    startActivity(irMapa);
                }
            }
        });
    }


    private boolean validacao(){
        String mensagem = "";

        TextView nomeUser = (TextView) findViewById(R.id.txtConfigNomeUsuario);
        TextView emailUser = (TextView) findViewById(R.id.txtEmailUsuario);
        TextView senhaUser = (TextView) findViewById(R.id.txtSenhaUsuario);


        if(nomeUser.getText().toString().equals("")) {
            mensagem += "\nDigite o Nome !";
        }

        if(emailUser.getText().toString().equals("")) {
            mensagem += "\nDigite o Email !";
        }
        if(senhaUser.getText().toString().equals("")) {
            mensagem += "\nDigite a Senha !";
        }



        if (!mensagem.equals("")) {
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroUsuario.this);
            builder.setTitle("Atenção !");
            builder.setNegativeButton("Fechar",null);
            builder.setMessage(mensagem);
            alerta = builder.create();
            alerta.show();
        }

        return mensagem.equals("");
    }




}
