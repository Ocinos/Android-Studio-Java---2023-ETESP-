package br.teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    MeuAdapter adapter;
    List<Contatos> lstContatos;

    private ProgressDialog mProgressDialog;
    List<String> mDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                new SincronismoHTTP().execute();

    }

    private class SincronismoHTTP extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Sincronizando");
            mProgressDialog.setMessage("Buscando Dados...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher_round);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String d = "http://10.0.2.2/consultaIMG.aspx";
                ConexaoHTTP.conectarHttp(d);
            }
            catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void vd){
            super.onPostExecute(vd);
            mDados = new ArrayList<String>();
            try{
                mDados = ConexaoHTTP.getDados();
                byte []x = hexToByteArray(mDados.get(3));
                Bitmap bitmap = BitmapFactory.decodeByteArray(x, 0, x.length);
                lstContatos = new ArrayList<>();
                for(int i = 0; i < mDados.size(); i++) {
                    String a = mDados.get(i);
                    String y[] = a.split(",");
                    int b = Integer.parseInt(y[3]);
                    lstContatos.add(new Contatos(y[0],y[1],y[2],b));
                }
            }catch(Exception err){
                Toast.makeText(getBaseContext(), "Erro "+err, Toast.LENGTH_LONG).show();
            }
            mProgressDialog.dismiss();
        }

        public byte[] hexToByteArray(String hex) {
            hex = hex.length()%2 != 0?"0"+hex:hex;

            byte[] b = new byte[hex.length() / 2];

            for (int i = 0; i < b.length; i++) {
                int index = i * 2;
                int v = Integer.parseInt(hex.substring(index, index + 2), 16);
                b[i] = (byte) v;
            }
            return b;
        }
    }
}