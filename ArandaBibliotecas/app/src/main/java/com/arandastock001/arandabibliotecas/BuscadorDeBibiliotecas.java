package com.arandastock001.arandabibliotecas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arandastock001.arandabibliotecas.Modelo.Ciudad;
import com.arandastock001.arandabibliotecas.Modelo.DB.Data;
import com.arandastock001.arandabibliotecas.Modelo.Region;

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
    private List<Ciudad> listaCiudadesPresentesEnLaRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_de_bibiliotecas);
        init();
        cargarSpinnerDeRegiones();
        cargarSpinnerDeCiudades();
        cargarTipos();


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
                    startActivity(new Intent(BuscadorDeBibiliotecas.this, ListadoDeBibliotecas.class));
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
        db.insertarRegion(new Region(1,"Antofagasta"));
        db.insertarRegion(new Region(1,"Ohiggins"));
        db.insertarRegion(new Region(1,"La Araucanía"));


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
        db.insertarCiudad(new Ciudad(1,"Primera ciudad de region 1",2));
        db.insertarCiudad(new Ciudad(1,"Segunda ciudad de region 1",2));
        db.insertarCiudad(new Ciudad(1,"Tercera ciudad de region 1",2));
        db.insertarCiudad(new Ciudad(1,"Primera ciudad de region 2",3));
        db.insertarCiudad(new Ciudad(1,"Segunda ciudad de region 2",3));
        db.insertarCiudad(new Ciudad(1,"Tercera ciudad de region 2",3));
        db.insertarCiudad(new Ciudad(1,"Primera ciudad de region 3",4));
        db.insertarCiudad(new Ciudad(1,"Segunda ciudad de region 3",4));
        db.insertarCiudad(new Ciudad(1,"Tercera ciudad de region 3",4));

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
        listaCiudadesPresentesEnLaRegion = db.getCiudadesDeLaRegion(r.getId());
        adaptadorCiudades = new ArrayAdapter<Ciudad>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaCiudadesPresentesEnLaRegion);
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




}
