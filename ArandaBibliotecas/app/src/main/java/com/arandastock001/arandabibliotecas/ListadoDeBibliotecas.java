package com.arandastock001.arandabibliotecas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;

import java.util.List;

public class ListadoDeBibliotecas extends AppCompatActivity {

private List<Biblioteca> bibliotecasFiltradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_bibliotecas);

        Intent i = getIntent();
        bibliotecasFiltradas = (List<Biblioteca>) i.getSerializableExtra("bibliotecasEncontradas");


        for (Biblioteca b:bibliotecasFiltradas) {
            System.out.println(b.getNombre());
        }
    }



}
