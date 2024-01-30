package br.teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
                                                                //CLICK NA LISTA
public class MainActivity extends AppCompatActivity implements ItemClickListener{

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;

    private ProgressDialog mProgressDialog;
    List<String> mDados;

    Bitmap[]img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecyclerView=findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        new SincronismoHTTP().execute();
    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new ModelClass(img[0],"Chiquinha","Ah chavinho","8:20","_______________________________________"));
        userList.add(new ModelClass(null,"Nhonho","O meu pai vai cobrar?","09:10","_______________________________________"));
        userList.add(new ModelClass(null,"Quico","Não me irrite?","10:00","_______________________________________"));
        userList.add(new ModelClass(null,"Seu Barriga","14 Meses.","11:10","_______________________________________"));
        userList.add(new ModelClass(null,"Seu Madruga","Cale-se chavinho","00:00","_______________________________________"));
        adapter=new Adapter(userList);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setClickListener(MainActivity.this); //CLICK NA LISTA
    }

    @Override //CLICK NA LISTA
    public void onClick(View view, int position) {
        ModelClass ct = userList.get(position);
        Toast.makeText(getBaseContext(),ct.getTextview1()+"",Toast.LENGTH_SHORT).show();
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
                ConexaoHTTP.conectarHttp("http://10.0.2.2/consultaIMG.aspx");
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
            try{
                mDados = ConexaoHTTP.getDados();
                userList = new ArrayList<>();
                img = new Bitmap[mDados.size()];
                for(int i=0;i<mDados.size();i++) {
                    byte[] x = hexToByteArray(mDados.get(i));
                    img[i] = BitmapFactory.decodeByteArray(x, 0, x.length);
                }
            }catch(Exception err){
                Toast.makeText(getBaseContext(), "Erro "+err, Toast.LENGTH_LONG).show();
            }
            mProgressDialog.dismiss();
            initData();
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