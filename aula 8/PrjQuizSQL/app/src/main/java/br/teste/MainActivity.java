package br.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button BtnNext;
    RadioButton RdoP1, RdoP2, RdoP3, RdoP4;
    TextView LblPergunta, LblPontos;

     static int contador = 0;
    ImageView ImgPergunta;

    private ProgressDialog mProgressDialog;
    List<String> mDados;
    static String []certo = new String[4];

    static int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnNext = findViewById(R.id.BtnNext);
        RdoP1 = findViewById(R.id.RdoP1);
        RdoP2 = findViewById(R.id.RdoP2);
        RdoP3 = findViewById(R.id.RdoP3);
        RdoP4 = findViewById(R.id.RdoP4);
        ImgPergunta = findViewById(R.id.ImgPergunta);
        LblPergunta = findViewById(R.id.LblPergunta);
        LblPontos = findViewById(R.id.LblPontos);

        ImgPergunta.setImageResource(R.drawable.yugioh);

        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BtnNext.getText().equals("Começar")) {
                    contador++;
                    BtnNext.setText("Próximo");
                    RdoP1.setVisibility(View.VISIBLE);
                    RdoP2.setVisibility(View.VISIBLE);
                    RdoP3.setVisibility(View.VISIBLE);
                    RdoP4.setVisibility(View.VISIBLE);
                    RdoP1.setChecked(false);
                    RdoP2.setChecked(false);
                    RdoP3.setChecked(false);
                    RdoP4.setChecked(false);
                    ImgPergunta.setImageResource(R.drawable.setokaiba);
                    new MainActivity.SincronismoHTTP().execute();
                }
                else if(BtnNext.getText().equals("Recomeçar")){
                    LblPontos.setText("");
                    pontos = 0;
                    BtnNext.setText("Começar");
                    ImgPergunta.setImageResource(R.drawable.yugioh);
                }
                else {
                    if (RdoP1.isChecked() || RdoP2.isChecked() || RdoP3.isChecked() || RdoP4.isChecked()) {
                        contador++;
                        new MainActivity.SincronismoHTTP().execute();
                        if (certo[0].equals("True") && RdoP1.isChecked())
                            pontos++;
                        else if (certo[1].equals("True") && RdoP2.isChecked())
                            pontos++;
                        else if (certo[2].equals("True") && RdoP3.isChecked())
                            pontos++;
                        else if (certo[3].equals("True") && RdoP4.isChecked())
                            pontos++;

                        switch (contador) {
                            case 2:
                                RdoP1.setChecked(false);
                                RdoP2.setChecked(false);
                                RdoP3.setChecked(false);
                                RdoP4.setChecked(false);
                                ImgPergunta.setImageResource(R.drawable.blueeyes);
                                break;
                            case 3:
                                RdoP1.setChecked(false);
                                RdoP2.setChecked(false);
                                RdoP3.setChecked(false);
                                RdoP4.setChecked(false);
                                ImgPergunta.setImageResource(R.drawable.spell);
                                break;
                            case 4:
                                RdoP1.setChecked(false);
                                RdoP2.setChecked(false);
                                RdoP3.setChecked(false);
                                RdoP4.setChecked(false);
                                ImgPergunta.setImageResource(R.drawable.gx);
                                break;
                        }
                        if (contador > 4) {
                            contador = 0;
                            LblPergunta.setText("Quiz de Yu-Gi-Oh!");
                            ImgPergunta.setImageResource(R.drawable.yugioh);
                            BtnNext.setText("Recomeçar");
                            RdoP1.setVisibility(View.INVISIBLE);
                            RdoP2.setVisibility(View.INVISIBLE);
                            RdoP3.setVisibility(View.INVISIBLE);
                            RdoP4.setVisibility(View.INVISIBLE);

                            switch (pontos) {
                                case 0:
                                    LblPontos.setText("Que pena, você acertou nenhuma questão!");
                                    break;
                                case 1:
                                    LblPontos.setText("Você acertou " + pontos + " de 4 questões, tente mais na próxima!");
                                    break;
                                case 2:
                                    LblPontos.setText("Você acertou " + pontos + " de 4 questões, boa!");
                                    break;
                                case 3:
                                    LblPontos.setText("Você acertou " + pontos + " de 4 questões, ótimo!");
                                    break;
                                case 4:
                                    LblPontos.setText("Você acertou " + pontos + " de 4 questões!!! Você é um duelista de verdade!");
                                    break;
                            }
                        }


                    }
                }
            }
        });
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
                    ConexaoHTTP.conectarHttp("http://10.0.2.2/consulta.aspx?id="+contador);
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
                ArrayAdapter<String> adp = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,mDados);
                ArrayList<String> jooj = new ArrayList<String>();


                for(int i = 0; i < mDados.size(); i++){
                    String x = mDados.get(i);
                    String []y = x.split(",");
                    switch (i){
                        case 0:
                            LblPergunta.setText(y[0]);
                            RdoP1.setText(y[1]);
                            certo[0] = y[2];
                            break;
                        case 1:
                            RdoP2.setText(y[1]);
                            certo[1] = y[2];
                            break;
                        case 2:
                            RdoP3.setText(y[1]);
                            certo[2] = y[2];
                            break;
                        case 3:
                            RdoP4.setText(y[1]);
                            certo[3] = y[2];
                            break;
                    }
                }
                mProgressDialog.dismiss();

                /*lstContatos = new ArrayList<>();

                for(int i = 0; i < mDados.size(); i++){
                    String x = mDados.get(i);
                    String []y = x.split(",");
                    byte[] j = hexToByteArray(y[3]);
                    img[i] = BitmapFactory.decodeByteArray(j, 0, j.length);
                    lstContatos.add(new Contatos(y[0],y[1],y[2],img[0]));
                }
                adapter = new MeuAdapter(lstContatos, MainActivity.this);
                recyclerView.setAdapter(adapter);
                mProgressDialog.dismiss();*/
            }
        }

        /*public byte[] hexToByteArray(String hex) {
            hex = hex.length()%2 != 0?"0"+hex:hex;

            byte[] b = new byte[hex.length() / 2];

            for (int i = 0; i < b.length; i++) {
                int index = i * 2;
                int v = Integer.parseInt(hex.substring(index, index + 2), 16);
                b[i] = (byte) v;
            }
            return b;
        }*/
    }
