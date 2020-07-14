package com.arandastock001.arandabibliotecas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;

import java.io.Serializable;
import java.util.List;


public class InformacionBasicaDeLaBiblioteca extends AppCompatActivity {


    private TextView nombre, direccion, telefono;
    private Button btnInfoDetallada, btnVerUbicacion;
    private Biblioteca b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_basica_de_la_biblioteca);

        init();

    }

    private void init() {

        btnInfoDetallada = findViewById(R.id.btnVerInformacionDetallada);
        btnVerUbicacion = findViewById(R.id.btnVerUbicacionInfoBasica);

        nombre=findViewById(R.id.lblNombreAVerInfoBasica);
        direccion=findViewById(R.id.lblDireccionAVerInfoBasica);
        telefono=findViewById(R.id.lblTelefonoAVerInfoBasica);

        Intent i = getIntent();

        b = ((Biblioteca) i.getSerializableExtra("bibliotecaSeleccionada"));


        nombre.setText(b.getNombre());
        direccion.setText(b.getDireccion());
        telefono.setText(b.getTelefono());

        btnInfoDetallada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformacionBasicaDeLaBiblioteca.this, InformacionDetalladaDeLaBiblioteca.class).putExtra("bibliotecaSeleccionada", (Serializable) b));
                finish();

            }
        });

        btnVerUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformacionBasicaDeLaBiblioteca.this, UbicacionDeLaBiblioteca.class));
                finish();
            }
        });





    }
}
