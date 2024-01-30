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

public class Excluir extends AppCompatActivity {
    Button BtnVoltar, BtnExcluir;
    TextView LblMsg;
    EditText TxtID;
    private ProgressDialog mProgressDialog;
    List<String> mDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
        BtnExcluir = findViewById(R.id.BtnExcluir);
        BtnVoltar = findViewById(R.id.BtnVoltar);
        LblMsg = findViewById(R.id.LblMsg);
        TxtID = findViewById(R.id.TxtID);
        BtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), MainActivity.class);
                startActivity(it);
            }
        });
        BtnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Excluir.SincronismoHTTP().execute();
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
                String id = TxtID.getText().toString();
                ConexaoHTTP.conectarHttp("http://10.0.2.2/excluir.aspx?id="+id);
            }
            catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void vd){
            super.onPostExecute(vd);
            mDados = new ArrayList<String>();
            mDados = ConexaoHTTP.getDados();
            if(mDados.size()==0)
                LblMsg.setText("Dados excluídos com sucesso");
            else
                LblMsg.setText("Erro ao excluir os dados");
            mProgressDialog.dismiss();
        }
    }
}