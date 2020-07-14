package com.arandastock001.arandabibliotecas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_icon, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "ArandaBibliotecas, creada por Marcelo Aranda el 16/7/2020. Se usó Google Maps",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Espere por favor",Toast.LENGTH_LONG).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



}
