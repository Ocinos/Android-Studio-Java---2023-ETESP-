package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Alterar extends AppCompatActivity {
Button BtnVoltar, BtnAlterar;
EditText TxtNome, TxtFone, TxtID;
private ProgressDialog mProgressDialog;
List<String> mDados;
TextView LblMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);
        BtnAlterar = findViewById(R.id.BtnAlterar);
        BtnVoltar = findViewById(R.id.BtnVoltar);
        TxtFone = findViewById(R.id.TxtFone);
        TxtNome = findViewById(R.id.TxtNome);
        TxtID = findViewById(R.id.TxtID);
        LblMsg = findViewById(R.id.LblMsg);
        BtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), MainActivity.class);
                startActivity(it);
            }
        });
        BtnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Alterar.SincronismoHTTP().execute();
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
                String id = TxtID.getText().toString();
                ConexaoHTTP.conectarHttp("http://10.0.2.2/update.aspx?id="+id+"&nome="+nome+"&fone="+fone);
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
                LblMsg.setText("Dados atualizados com sucesso");
            else
                LblMsg.setText("Erro ao atualizar os dados");
            mProgressDialog.dismiss();
        }
    }
}