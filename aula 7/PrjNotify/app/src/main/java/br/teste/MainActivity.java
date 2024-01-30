package br.teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnNotify = findViewById(R.id.BtnNotify);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notify", "Notify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        BtnNotify.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder not = new NotificationCompat.Builder(getBaseContext(), "Notify");
                not.setContentTitle("Jooj");
                not.setContentText("Agaragã");
                not.setSmallIcon(R.drawable.baseline_notifications_24);
                not.setAutoCancel(true);

                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.notify(1, not.build());
            }
        });
    }
}