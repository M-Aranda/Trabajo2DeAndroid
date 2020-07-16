package com.arandastock001.arandabibliotecas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;
import com.arandastock001.arandabibliotecas.Modelo.Ciudad;
import com.arandastock001.arandabibliotecas.Modelo.DB.Data;
import com.arandastock001.arandabibliotecas.Modelo.Region;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuscadorDeBibiliotecas extends AppCompatActivity {


    private Spinner spnRegion, spnCiudad, spnTipo;
    private Button btnFiltrar;
    private Data db;

    private List<Region> listaRegiones;
    private ArrayAdapter<Region> adaptadorRegiones;

    private List<Ciudad> listaCiudades;
    private ArrayAdapter<Ciudad> adaptadorCiudades;

    private List<Biblioteca> listaDeBibliotecasEncontradas;

    private  List<Ciudad> ciudadesEncontradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_de_bibiliotecas);
        init();
        cargarSpinnerDeRegiones();
        cargarSpinnerDeCiudades();
        cargarTipos();
        cargarBibliotecas();


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
                Toast.makeText(this, "Ya se encuentra en el menú de búsqueda",Toast.LENGTH_LONG).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void init(){
        spnRegion = findViewById(R.id.spnRegion);
        spnCiudad = findViewById(R.id.spnCiudad);
        spnTipo = findViewById(R.id.spnTipo);

        spnRegion.setPrompt("Región");
        spnCiudad.setPrompt("Ciudad");
        spnTipo.setPrompt("Tipo");


        btnFiltrar = findViewById(R.id.btnFiltrarBuscadorDeBibliotecas);




        db = new Data(this.getApplicationContext());
        db.reiniciar();



        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spnCiudad.getSelectedItemPosition()==0 || spnTipo.getSelectedItemPosition()==0 || spnTipo.getSelectedItemPosition()==0){
                    System.out.println("Falta elegir algun dato");
                    Toast t = Toast.makeText(BuscadorDeBibiliotecas.this,"Falta elegir algun dato", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();


                }else{
                    Ciudad c = ((Ciudad) spnCiudad.getSelectedItem());
                    int esPublica = 1;
                    if(String.valueOf(spnTipo.toString())=="Privada"){
                        esPublica = 0;
                    }

                    listaDeBibliotecasEncontradas = db.getBibliotecasFiltradas(c.getId(), esPublica);

                    spnRegion.setSelection(0);
                    spnCiudad.setSelection(0);
                    spnTipo.setSelection(0);

                    startActivity(new Intent(BuscadorDeBibiliotecas.this, ListadoDeBibliotecas.class).putExtra("bibliotecasEncontradas", (Serializable) listaDeBibliotecasEncontradas));
                    finish();
                }




            }
        });

        spnRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                cargarSpinnerDeCiudadesEnLaRegion();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }





    public void cargarSpinnerDeRegiones(){

        db.insertarRegion(new Region(1,"Región"));
        db.insertarRegion(new Region(1,"Arica y Parinacota"));
        db.insertarRegion(new Region(1,"Tarapacá"));
        db.insertarRegion(new Region(1,"Antofagasta"));
        db.insertarRegion(new Region(1,"Atacama"));
        db.insertarRegion(new Region(1,"Coquimbo"));
        db.insertarRegion(new Region(1,"Valparaíso"));
        db.insertarRegion(new Region(1,"Metropolitana"));
        db.insertarRegion(new Region(1,"OHiggins"));
        db.insertarRegion(new Region(1,"Maule"));
        db.insertarRegion(new Region(1,"Ñuble"));
        db.insertarRegion(new Region(1,"Bío Bío"));
        db.insertarRegion(new Region(1,"La Araucanía"));
        db.insertarRegion(new Region(1,"Los Ríos"));
        db.insertarRegion(new Region(1,"Los Lagos"));
        db.insertarRegion(new Region(1,"Aysén del General Carlos Ibáñez del Campo"));
        db.insertarRegion(new Region(1,"Magallanes y la Antártica Chilena"));



        listaRegiones= db.getRegiones();

        if(!listaRegiones.isEmpty()){
            adaptadorRegiones = new ArrayAdapter<Region>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaRegiones)//;
            {
                @Override
                public boolean isEnabled(int position){
                    if(position == 0)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };

            spnRegion.setAdapter(adaptadorRegiones);
            System.out.println("La lista obtenida contiene "+listaRegiones.size() +" entradas");

        }else{
            System.out.println("La tabla no tiene datos");
        }
    }

    public void cargarSpinnerDeCiudades(){



        db.insertarCiudad(new Ciudad(1,"Ciudad",1));
        db.insertarCiudad(new Ciudad(1,"Arica",2));
        db.insertarCiudad(new Ciudad(1,"Visviri",2));
        db.insertarCiudad(new Ciudad(1,"Putre",2));


        listaCiudades= db.getCiudades();

        if(!listaCiudades.isEmpty()){
            adaptadorCiudades = new ArrayAdapter<Ciudad>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaCiudades)//;
            {
                @Override
                public boolean isEnabled(int position){
                    if(position == 0)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };


            spnCiudad.setAdapter(adaptadorCiudades);
            System.out.println("La lista obtenida contiene "+listaCiudades.size() +" entradas");

        }else{
            System.out.println("La tabla no tiene datos");
        }
    }


    public void cargarSpinnerDeCiudadesEnLaRegion(){



        Region r = (Region)spnRegion.getSelectedItem();
        ciudadesEncontradas = db.getCiudadesDeLaRegion(r.getId());
        adaptadorCiudades = new ArrayAdapter<Ciudad>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ciudadesEncontradas)//;
        {
            @Override
            public boolean isEnabled(int position){
            if(position == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
            @Override
            public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            if(position == 0){
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
            }
            else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }
        };
        spnCiudad.setAdapter(adaptadorCiudades);
    }

    public void cargarTipos(){

        List<String> tipos = new ArrayList<>();
        tipos.add("Tipo");
        tipos.add("Pública");
        tipos.add("Privada");
        ArrayAdapter<String> adaptadorSpnTipo = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,tipos)
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spnTipo.setAdapter(adaptadorSpnTipo);
    }



    private void cargarBibliotecas() {
        db.insertarBiblioteca(new Biblioteca(1,"Alfredo Wormald Cruz Arica", "Baquedano 94, Arica, Arica y Parinacota", "(58) 238 6587",
                "No se encontró", 1, 2,-18.481197,-70.319766));


        db.insertarBiblioteca(new Biblioteca(1,"Los industriales de Arica", "403 BC1, Biblioteca Pública Industriales\n" +
                "Calle Samo Alto 3347\n" +
                "Arica, Arica, Región de Arica y Parinacota", "58 2386581 / 58 2386582",
                "https://www.biblioredes.gob.cl/bibliotecas/arica/industriales", 1, 2,-18.454388,-70.281407));

        db.insertarBiblioteca(new Biblioteca(1,"Central universidad de Tarapacá", "18 de Septiembre 2222", "No se encontró",
                "http://sb.uta.cl/sbuta/", 0, 2,-18.490058,-70.295735));


    }


}
