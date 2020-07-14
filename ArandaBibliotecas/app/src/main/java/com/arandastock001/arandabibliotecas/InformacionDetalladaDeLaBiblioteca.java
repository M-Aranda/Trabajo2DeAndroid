package com.arandastock001.arandabibliotecas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;

import java.io.Serializable;

public class InformacionDetalladaDeLaBiblioteca extends AppCompatActivity {

    private TextView nombre, direccion, telefono, sitioWeb, tipo;
    private Button btnInfoBasica, btnVerUbicacion;
    private Biblioteca b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_detallada_de_la_biblioteca);

        init();

    }

    private void init() {
        nombre = findViewById(R.id.lblNombreAVerInfoDetallada);
        direccion = findViewById(R.id.lblDireccionAVerInfoDetallada);
        telefono = findViewById(R.id.lblTelefonoAVerInfoDetallada);
        sitioWeb = findViewById(R.id.lblSitioWebAVerInfoDetallada);
        tipo = findViewById(R.id.lblTipoAVerInfoDetallada);

        btnInfoBasica = findViewById(R.id.btnInformacionBasica);
        btnVerUbicacion = findViewById(R.id.btnVerUbicacionInfoDetallada);

        Intent i = getIntent();

        b = ((Biblioteca) i.getSerializableExtra("bibliotecaSeleccionada"));

        String privadaOPublica = "Pública";
        if(b.getEsPublica() == 0){
            privadaOPublica = "Privada";
        }

        nombre.setText(b.getNombre());
        direccion.setText(b.getDireccion());
        telefono.setText(b.getTelefono());
        sitioWeb.setText(b.getSitioWeb());
        tipo.setText(privadaOPublica);


        btnInfoBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformacionDetalladaDeLaBiblioteca.this, InformacionBasicaDeLaBiblioteca.class).putExtra("bibliotecaSeleccionada", (Serializable) b));
                finish();
            }
        });

        btnVerUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformacionDetalladaDeLaBiblioteca.this, UbicacionDeLaBiblioteca.class));
                finish();
            }
        });

    }
}
