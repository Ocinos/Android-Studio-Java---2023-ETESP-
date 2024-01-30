package br.teste;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Inserir extends AppCompatActivity {
    Button BtnInserir, BtnVoltar;
    EditText TxtNome, TxtFone;
    private ProgressDialog mProgressDialog;
    List<String> mDados;
    TextView LblMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);
        BtnInserir = findViewById(R.id.BtnInserir);
        BtnVoltar = findViewById(R.id.BtnVoltar);
        TxtFone = findViewById(R.id.TxtFone);
        TxtNome = findViewById(R.id.TxtNome);
        LblMsg = findViewById(R.id.LblMsg);
        BtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), MainActivity.class);
                startActivity(it);
            }
        });
        BtnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Inserir.SincronismoHTTP().execute();
            }
        });


    }
    private class SincronismoHTTP extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getBaseContext());
            mProgressDialog.setTitle("Sincronizando");
            mProgressDialog.setMessage("Buscando Dados...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher_round);
            mProgressDialog.setCancelable(false);
            // mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String nome = TxtNome.getText().toString();
                String fone = TxtFone.getText().toString();
                ConexaoHTTP.conectarHttp("http://10.0.2.2/inserir.aspx?nome="+nome+"&fone="+fone);
            }
            catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void vd){
            super.onPostExecute(vd);
            mDados = new ArrayList<String>();
            mDados = ConexaoHTTP.getDados();
            if(mDados.size()>0)
                LblMsg.setText("Cadastro concluído com sucesso");
            else
                LblMsg.setText("Erro ao efetuar o cadastro");
            mProgressDialog.dismiss();
        }
    }
}