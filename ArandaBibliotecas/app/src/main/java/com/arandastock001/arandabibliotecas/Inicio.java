package com.arandastock001.arandabibliotecas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast t = Toast.makeText(Inicio.this,"Bienvenido a la aplicación", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();

        //Intent i = new Intent(getApplicationContext(),BuscadorDeBibiliotecas.class);
       // startActivity(i);
        // finish();


        Runnable r = new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(Inicio.this, BuscadorDeBibiliotecas.class));
                finish();

            }
        };


        Handler h = new Handler();

        h.postDelayed(r, 3000);


    }



}
