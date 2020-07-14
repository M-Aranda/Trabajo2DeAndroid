package com.arandastock001.arandabibliotecas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;

import java.io.Serializable;
import java.util.List;

public class ListadoDeBibliotecas extends AppCompatActivity {

private List<Biblioteca> bibliotecasFiltradas;
private ListView listViewBibliotecasEncontradas;
private ArrayAdapter<Biblioteca> adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_bibliotecas);

        Intent i = getIntent();
        bibliotecasFiltradas = (List<Biblioteca>) i.getSerializableExtra("bibliotecasEncontradas");


        for (Biblioteca b:bibliotecasFiltradas) {
            System.out.println(b.getNombre());
        }

        init();

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
                startActivity(new Intent(ListadoDeBibliotecas.this, BuscadorDeBibiliotecas.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

        listViewBibliotecasEncontradas = findViewById(R.id.list_view_bibliotecas_encontradas);
        adaptadorLista=new ArrayAdapter(this,android.R.layout.simple_list_item_1,bibliotecasFiltradas);
        listViewBibliotecasEncontradas.setAdapter(adaptadorLista);

        listViewBibliotecasEncontradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Biblioteca b =(Biblioteca) listViewBibliotecasEncontradas.getItemAtPosition(position);

                startActivity(new Intent(ListadoDeBibliotecas.this, InformacionBasicaDeLaBiblioteca.class).putExtra("bibliotecaSeleccionada", (Serializable) b));
                finish();

            }
        });





    }



}
