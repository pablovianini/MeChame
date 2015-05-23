package com.mapa.mechame.mechame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;


public class ConfirmaTelefone extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UsuarioDAO dao = new UsuarioDAO(this);

        if(dao.login()){
            Intent itProsseguir = new Intent(ConfirmaTelefone.this, MapsActivity.class);
            startActivity(itProsseguir);
            finish();
        }
        setContentView(R.layout.confirma_telefone);


        /*Fazer a Validação para saber se o usuario já é cadastrado, caso seja mandar para a Classe Login Usuario!!*/

        ImageView btnConfirmaTelefone = (ImageView) findViewById(R.id.btnValidarTelefone);

        btnConfirmaTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView txtTelefone = (TextView) findViewById(R.id.txtTelefone);
                if(validacao(txtTelefone.getText().toString())){

                    Intent itProsseguir = new Intent(ConfirmaTelefone.this, CadastroUsuario.class);
                    itProsseguir.putExtra("telefone",txtTelefone.getText().toString());
                    startActivity(itProsseguir);
                    finish();

                }
            }
        });


    }


    private boolean validacao(String telefone){
        String mensagem = "";

        if(telefone.equals("")) {
            mensagem += "\nTelefone Obrigatório";
        }

        if(telefone.length() < 13)
           mensagem +="\nDigite corretamente o Telefone !";

        if (!mensagem.equals("")) {
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmaTelefone.this);
            builder.setTitle("Atenção !");
            builder.setNegativeButton("Fechar",null);
            builder.setMessage(mensagem);
            alerta = builder.create();
            alerta.show();
        }

        return mensagem.equals("");
    }
}
