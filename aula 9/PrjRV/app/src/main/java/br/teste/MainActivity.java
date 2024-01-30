package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Jooj{

    private RecyclerView recyclerView;
    MeuAdapter adapter;
    List<Contatos> lstContatos;


    private ProgressDialog mProgressDialog;
    List<String> mDados;

    String nome, sobrenome, idade = "";

    int[] a = {R.drawable.spell, R.drawable.earth, R.drawable.trap, R.drawable.light};

    Bitmap[] img = new Bitmap[4];
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        new MainActivity.SincronismoHTTP().execute();





    }

    @Override
    public void onItemClick(int pos) {
       nome = lstContatos.get(pos).getNome();
       sobrenome = lstContatos.get(pos).getSobrenome();
       idade = lstContatos.get(pos).getIdade();
        Toast.makeText(this, "Nome: " + nome +"\nSobrenome: "+ sobrenome +"\nIdade: "+ idade, Toast.LENGTH_SHORT).show();

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
                ConexaoHTTP.conectarHttp("http://10.0.2.2/consulta.aspx");
            }
            catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void vd){
            super.onPostExecute(vd);
            mDados = new ArrayList<String>();
            mDados = ConexaoHTTP.getDados();
            //Toast.makeText(getBaseContext(),mDados.get(0),Toast.LENGTH_LONG).show();
            //ArrayAdapter<String> adp = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,mDados);


            lstContatos = new ArrayList<>();

            for(int i = 0; i < mDados.size(); i++){
                String x = mDados.get(i);
                String []y = x.split(",");
                byte[] j = hexToByteArray(y[3]);
                img[i] = BitmapFactory.decodeByteArray(j, 0, j.length);
                lstContatos.add(new Contatos(y[0],y[1],y[2],img[0]));
            }
            adapter = new MeuAdapter(lstContatos, MainActivity.this);
            recyclerView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
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