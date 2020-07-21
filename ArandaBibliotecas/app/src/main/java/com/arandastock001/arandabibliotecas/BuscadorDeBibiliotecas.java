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

                    if((spnTipo.getSelectedItem().toString())=="Privada"){
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

        db.insertarRegion(new Region(1,"Región"));//1
        db.insertarRegion(new Region(1,"Arica y Parinacota"));//2
        db.insertarRegion(new Region(1,"Tarapacá"));//3
        db.insertarRegion(new Region(1,"Antofagasta"));//4
        db.insertarRegion(new Region(1,"Atacama"));//5
        db.insertarRegion(new Region(1,"Coquimbo"));//6
        db.insertarRegion(new Region(1,"Valparaíso"));//7
        db.insertarRegion(new Region(1,"OHiggins"));//8
        db.insertarRegion(new Region(1,"Maule"));//9
        db.insertarRegion(new Region(1,"Ñuble"));//10
        db.insertarRegion(new Region(1,"Bío Bío"));//11
        db.insertarRegion(new Region(1,"La Araucanía"));//12
        db.insertarRegion(new Region(1,"Los Ríos"));//13
        db.insertarRegion(new Region(1,"Los Lagos"));//14
        db.insertarRegion(new Region(1,"Aysén del General Carlos Ibáñez del Campo"));//15
        db.insertarRegion(new Region(1,"Magallanes y la Antártica Chilena"));//16
        db.insertarRegion(new Region(1,"Metropolitana"));//17



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
        db.insertarCiudad(new Ciudad(1,"Camarones",2));
        db.insertarCiudad(new Ciudad(1,"Putre",2));
        db.insertarCiudad(new Ciudad(1,"General Lagos",2));

        db.insertarCiudad(new Ciudad(1,"Iquique",3));
        db.insertarCiudad(new Ciudad(1,"Alto Hospicio",3));
        db.insertarCiudad(new Ciudad(1,"Pozo Almonte",3));
        db.insertarCiudad(new Ciudad(1,"Camiña",3));
        db.insertarCiudad(new Ciudad(1,"Colchane",3));
        db.insertarCiudad(new Ciudad(1,"Huara",3));
        db.insertarCiudad(new Ciudad(1,"Pica",3));

        db.insertarCiudad(new Ciudad(1,"Antofagasta",4));
        db.insertarCiudad(new Ciudad(1,"Mejillones",4));
        db.insertarCiudad(new Ciudad(1,"Sierra Gorda",4));
        db.insertarCiudad(new Ciudad(1,"Taltal",4));
        db.insertarCiudad(new Ciudad(1,"Calama",4));
        db.insertarCiudad(new Ciudad(1,"Ollagüe",4));
        db.insertarCiudad(new Ciudad(1,"San Pedro de Atacama",4));
        db.insertarCiudad(new Ciudad(1,"Tocpilla",4));
        db.insertarCiudad(new Ciudad(1,"María Elena",4));

        db.insertarCiudad(new Ciudad(1,"Copiapó",5));
        db.insertarCiudad(new Ciudad(1,"Caldera",5));
        db.insertarCiudad(new Ciudad(1,"Tierra Amarilla",5));
        db.insertarCiudad(new Ciudad(1,"Chañaral",5));
        db.insertarCiudad(new Ciudad(1,"Diego de Almagro",5));
        db.insertarCiudad(new Ciudad(1,"Vallenar",5));
        db.insertarCiudad(new Ciudad(1,"Alto del Carmen",5));
        db.insertarCiudad(new Ciudad(1,"Freirina",5));
        db.insertarCiudad(new Ciudad(1,"Huasco",5));

        db.insertarCiudad(new Ciudad(1,"La Serena",6));
        db.insertarCiudad(new Ciudad(1,"Coquimbo",6));
        db.insertarCiudad(new Ciudad(1,"Andacollo",6));
        db.insertarCiudad(new Ciudad(1,"La Higuera",6));
        db.insertarCiudad(new Ciudad(1,"Paihuano",6));
        db.insertarCiudad(new Ciudad(1,"Vicuña",6));
        db.insertarCiudad(new Ciudad(1,"Illapel",6));
        db.insertarCiudad(new Ciudad(1,"Canela",6));
        db.insertarCiudad(new Ciudad(1,"Los Vilos",6));
        db.insertarCiudad(new Ciudad(1,"Salamanca",6));
        db.insertarCiudad(new Ciudad(1,"Ovalle",6));
        db.insertarCiudad(new Ciudad(1,"Combarabalá",6));
        db.insertarCiudad(new Ciudad(1,"Monte Patria",6));
        db.insertarCiudad(new Ciudad(1,"Punitaqui",6));
        db.insertarCiudad(new Ciudad(1,"Río Hurtado",6));

        db.insertarCiudad(new Ciudad(1,"Valparaíso",7));
        db.insertarCiudad(new Ciudad(1,"Casablanca",7));
        db.insertarCiudad(new Ciudad(1,"Concón",7));
        db.insertarCiudad(new Ciudad(1,"Juan Fernández",7));
        db.insertarCiudad(new Ciudad(1,"Puchuncaví",7));
        db.insertarCiudad(new Ciudad(1,"Quintero",7));
        db.insertarCiudad(new Ciudad(1,"Viña del Mar",7));
        db.insertarCiudad(new Ciudad(1,"Isla de Pascua",7));
        db.insertarCiudad(new Ciudad(1,"Los Andes",7));
        db.insertarCiudad(new Ciudad(1,"Calle Larga",7));
        db.insertarCiudad(new Ciudad(1,"Rinconada",7));
        db.insertarCiudad(new Ciudad(1,"San Esteban",7));
        db.insertarCiudad(new Ciudad(1,"La Ligua",7));
        db.insertarCiudad(new Ciudad(1,"Cabildo",7));
        db.insertarCiudad(new Ciudad(1,"Papudo",7));
        db.insertarCiudad(new Ciudad(1,"Petorca",7));
        db.insertarCiudad(new Ciudad(1,"Zapallar",7));
        db.insertarCiudad(new Ciudad(1,"Quillota",7));
        db.insertarCiudad(new Ciudad(1,"La Calera",7));
        db.insertarCiudad(new Ciudad(1,"Hijuelas",7));
        db.insertarCiudad(new Ciudad(1,"La Cruz",7));
        db.insertarCiudad(new Ciudad(1,"Nogales",7));
        db.insertarCiudad(new Ciudad(1,"San Antonio",7));
        db.insertarCiudad(new Ciudad(1,"Algarrobo",7));
        db.insertarCiudad(new Ciudad(1,"Cartagena",7));
        db.insertarCiudad(new Ciudad(1,"El Quisco",7));
        db.insertarCiudad(new Ciudad(1,"El Tabo",7));
        db.insertarCiudad(new Ciudad(1,"Santo Domingo",7));
        db.insertarCiudad(new Ciudad(1,"San Felipe",7));
        db.insertarCiudad(new Ciudad(1,"Catemu",7));
        db.insertarCiudad(new Ciudad(1,"Llay-Llay",7));
        db.insertarCiudad(new Ciudad(1,"Panquehue",7));
        db.insertarCiudad(new Ciudad(1,"Putaendo",7));
        db.insertarCiudad(new Ciudad(1,"Santa María",7));
        db.insertarCiudad(new Ciudad(1,"Quilpué",7));
        db.insertarCiudad(new Ciudad(1,"Limache",7));
        db.insertarCiudad(new Ciudad(1,"Olmué",7));
        db.insertarCiudad(new Ciudad(1,"Villa Alemana",7));

        db.insertarCiudad(new Ciudad(1,"Rancagua",8));
        db.insertarCiudad(new Ciudad(1,"Codegua",8));
        db.insertarCiudad(new Ciudad(1,"Coinco",8));
        db.insertarCiudad(new Ciudad(1,"Coltauco",8));
        db.insertarCiudad(new Ciudad(1,"Doñihue",8));
        db.insertarCiudad(new Ciudad(1,"Graneros",8));
        db.insertarCiudad(new Ciudad(1,"Las Cabras",8));
        db.insertarCiudad(new Ciudad(1,"Machalí",8));
        db.insertarCiudad(new Ciudad(1,"Malloa",8));
        db.insertarCiudad(new Ciudad(1,"Mostazal",8));
        db.insertarCiudad(new Ciudad(1,"Olivar",8));
        db.insertarCiudad(new Ciudad(1,"Peumo",8));
        db.insertarCiudad(new Ciudad(1,"Pichidegua",8));
        db.insertarCiudad(new Ciudad(1,"Quinta de Tilcoco",8));
        db.insertarCiudad(new Ciudad(1,"Rengo",8));
        db.insertarCiudad(new Ciudad(1,"Requínoa",8));
        db.insertarCiudad(new Ciudad(1,"San Vicente",8));
        db.insertarCiudad(new Ciudad(1,"Pichilemu",8));
        db.insertarCiudad(new Ciudad(1,"La Estrella",8));
        db.insertarCiudad(new Ciudad(1,"Litueche",8));
        db.insertarCiudad(new Ciudad(1,"Marchihue",8));
        db.insertarCiudad(new Ciudad(1,"Navidad",8));
        db.insertarCiudad(new Ciudad(1,"Paredones",8));
        db.insertarCiudad(new Ciudad(1,"San Fernando",8));
        db.insertarCiudad(new Ciudad(1,"Chépica",8));
        db.insertarCiudad(new Ciudad(1,"Chimbarongo",8));
        db.insertarCiudad(new Ciudad(1,"Lolol",8));
        db.insertarCiudad(new Ciudad(1,"Nancagua",8));
        db.insertarCiudad(new Ciudad(1,"Palmilla",8));
        db.insertarCiudad(new Ciudad(1,"Peralillo",8));
        db.insertarCiudad(new Ciudad(1,"Placilla",8));
        db.insertarCiudad(new Ciudad(1,"Pumanque",8));
        db.insertarCiudad(new Ciudad(1,"Santa Cruz",8));

        db.insertarCiudad(new Ciudad(1,"Talca",9));
        db.insertarCiudad(new Ciudad(1,"Constitución",9));
        db.insertarCiudad(new Ciudad(1,"Curepto",9));
        db.insertarCiudad(new Ciudad(1,"Empedrado",9));
        db.insertarCiudad(new Ciudad(1,"Maule",9));
        db.insertarCiudad(new Ciudad(1,"Pelarco",9));
        db.insertarCiudad(new Ciudad(1,"Pencahue",9));
        db.insertarCiudad(new Ciudad(1,"Río Claro",9));
        db.insertarCiudad(new Ciudad(1,"San Clemente",9));
        db.insertarCiudad(new Ciudad(1,"San Rafael",9));
        db.insertarCiudad(new Ciudad(1,"Cauquenes",9));
        db.insertarCiudad(new Ciudad(1,"Chanco",9));
        db.insertarCiudad(new Ciudad(1,"Pelluhue",9));
        db.insertarCiudad(new Ciudad(1,"Curicó",9));
        db.insertarCiudad(new Ciudad(1,"Hualañé",9));
        db.insertarCiudad(new Ciudad(1,"Licantén",9));
        db.insertarCiudad(new Ciudad(1,"Molina",9));
        db.insertarCiudad(new Ciudad(1,"Rauco",9));
        db.insertarCiudad(new Ciudad(1,"Romeral",9));
        db.insertarCiudad(new Ciudad(1,"Sagrada Familia",9));
        db.insertarCiudad(new Ciudad(1,"Teno",9));
        db.insertarCiudad(new Ciudad(1,"Vichuquén",9));
        db.insertarCiudad(new Ciudad(1,"Linares",9));
        db.insertarCiudad(new Ciudad(1,"Colbún",9));
        db.insertarCiudad(new Ciudad(1,"Longaví",9));
        db.insertarCiudad(new Ciudad(1,"Parral",9));
        db.insertarCiudad(new Ciudad(1,"Retiro",9));
        db.insertarCiudad(new Ciudad(1,"San Javier",9));
        db.insertarCiudad(new Ciudad(1,"Villa Alegre",9));
        db.insertarCiudad(new Ciudad(1,"Yerbas Buenas",9));


        db.insertarCiudad(new Ciudad(1,"Chillán",10));
        db.insertarCiudad(new Ciudad(1,"Bulnes",10));
        db.insertarCiudad(new Ciudad(1,"Chillán Viejo",10));
        db.insertarCiudad(new Ciudad(1,"El Carmen",10));
        db.insertarCiudad(new Ciudad(1,"Pemuco",10));
        db.insertarCiudad(new Ciudad(1,"Pinto",10));
        db.insertarCiudad(new Ciudad(1,"Quillón",10));
        db.insertarCiudad(new Ciudad(1,"San Ignacio",10));
        db.insertarCiudad(new Ciudad(1,"Yungay",10));
        db.insertarCiudad(new Ciudad(1,"Quirihue",10));
        db.insertarCiudad(new Ciudad(1,"Cobquecura",10));
        db.insertarCiudad(new Ciudad(1,"Coelemu",10));
        db.insertarCiudad(new Ciudad(1,"Ninhue",10));
        db.insertarCiudad(new Ciudad(1,"Portezuelo",10));
        db.insertarCiudad(new Ciudad(1,"Ránquil",10));
        db.insertarCiudad(new Ciudad(1,"Treguaco",10));
        db.insertarCiudad(new Ciudad(1,"San Carlos",10));
        db.insertarCiudad(new Ciudad(1,"Coihueco",10));
        db.insertarCiudad(new Ciudad(1,"Ñiquén",10));
        db.insertarCiudad(new Ciudad(1,"San Fabián",10));
        db.insertarCiudad(new Ciudad(1,"San Nicolás",10));

        db.insertarCiudad(new Ciudad(1,"Concepción",11));
        db.insertarCiudad(new Ciudad(1,"Coronel",11));
        db.insertarCiudad(new Ciudad(1,"Chiguayante",11));
        db.insertarCiudad(new Ciudad(1,"Florida",11));
        db.insertarCiudad(new Ciudad(1,"Hualqui",11));
        db.insertarCiudad(new Ciudad(1,"Lota",11));
        db.insertarCiudad(new Ciudad(1,"Penco",11));
        db.insertarCiudad(new Ciudad(1,"San Pedro de La Paz",11));
        db.insertarCiudad(new Ciudad(1,"Santa Juana",11));
        db.insertarCiudad(new Ciudad(1,"Talcahuano",11));
        db.insertarCiudad(new Ciudad(1,"Tomé",11));
        db.insertarCiudad(new Ciudad(1,"Hualpén",11));
        db.insertarCiudad(new Ciudad(1,"Lebu",11));
        db.insertarCiudad(new Ciudad(1,"Arauco",11));
        db.insertarCiudad(new Ciudad(1,"Cañete",11));
        db.insertarCiudad(new Ciudad(1,"Contulmo",11));
        db.insertarCiudad(new Ciudad(1,"Curanilahue",11));
        db.insertarCiudad(new Ciudad(1,"Los Álamos",11));
        db.insertarCiudad(new Ciudad(1,"Tirúa",11));
        db.insertarCiudad(new Ciudad(1,"Los Ángeles",11));
        db.insertarCiudad(new Ciudad(1,"Antuco",11));
        db.insertarCiudad(new Ciudad(1,"Cabrero",11));
        db.insertarCiudad(new Ciudad(1,"Laja",11));
        db.insertarCiudad(new Ciudad(1,"Mulchén",11));
        db.insertarCiudad(new Ciudad(1,"Nacimiento",11));
        db.insertarCiudad(new Ciudad(1,"Negrete",11));
        db.insertarCiudad(new Ciudad(1,"Quilaco",11));
        db.insertarCiudad(new Ciudad(1,"Quilleco",11));
        db.insertarCiudad(new Ciudad(1,"San Rosendo",11));
        db.insertarCiudad(new Ciudad(1,"Santa Bárbara",11));
        db.insertarCiudad(new Ciudad(1,"Tucapel",11));
        db.insertarCiudad(new Ciudad(1,"Yumbel",11));
        db.insertarCiudad(new Ciudad(1,"Alto Biobío",11));

        db.insertarCiudad(new Ciudad(1,"Temuco",12));
        db.insertarCiudad(new Ciudad(1,"Carahue",12));
        db.insertarCiudad(new Ciudad(1,"Cunco",12));
        db.insertarCiudad(new Ciudad(1,"Curarrehue",12));
        db.insertarCiudad(new Ciudad(1,"Freire",12));
        db.insertarCiudad(new Ciudad(1,"Galvarino",12));
        db.insertarCiudad(new Ciudad(1,"Gorbea",12));
        db.insertarCiudad(new Ciudad(1,"Lautaro",12));
        db.insertarCiudad(new Ciudad(1,"Loncoche",12));
        db.insertarCiudad(new Ciudad(1,"Melipeuco",12));
        db.insertarCiudad(new Ciudad(1,"Nueva Imperial",12));
        db.insertarCiudad(new Ciudad(1,"Padre Las Casas",12));
        db.insertarCiudad(new Ciudad(1,"Perquenco",12));
        db.insertarCiudad(new Ciudad(1,"Pitrufquén",12));
        db.insertarCiudad(new Ciudad(1,"Pucón",12));
        db.insertarCiudad(new Ciudad(1,"Saavedra",12));
        db.insertarCiudad(new Ciudad(1,"Teodoro Schmidt",12));
        db.insertarCiudad(new Ciudad(1,"Toltén",12));
        db.insertarCiudad(new Ciudad(1,"Vilcún",12));
        db.insertarCiudad(new Ciudad(1,"Villarrica",12));
        db.insertarCiudad(new Ciudad(1,"Cholchol",12));
        db.insertarCiudad(new Ciudad(1,"Angol",12));
        db.insertarCiudad(new Ciudad(1,"Collipulli",12));
        db.insertarCiudad(new Ciudad(1,"Curacautín",12));
        db.insertarCiudad(new Ciudad(1,"Ercilla",12));
        db.insertarCiudad(new Ciudad(1,"Lonquimay",12));
        db.insertarCiudad(new Ciudad(1,"Los Sauces",12));
        db.insertarCiudad(new Ciudad(1,"Lumaco",12));
        db.insertarCiudad(new Ciudad(1,"Purén",12));
        db.insertarCiudad(new Ciudad(1,"Renaico",12));
        db.insertarCiudad(new Ciudad(1,"Traiguén",12));
        db.insertarCiudad(new Ciudad(1,"Victoria",12));

        db.insertarCiudad(new Ciudad(1,"Valdivia",13));
        db.insertarCiudad(new Ciudad(1,"Corral",13));
        db.insertarCiudad(new Ciudad(1,"Lanco",13));
        db.insertarCiudad(new Ciudad(1,"Los Lagos",13));
        db.insertarCiudad(new Ciudad(1,"Máfil",13));
        db.insertarCiudad(new Ciudad(1,"Mariquina",13));
        db.insertarCiudad(new Ciudad(1,"Paillaco",13));
        db.insertarCiudad(new Ciudad(1,"Panguipulli",13));
        db.insertarCiudad(new Ciudad(1,"La Unión",13));
        db.insertarCiudad(new Ciudad(1,"Futrono",13));
        db.insertarCiudad(new Ciudad(1,"Lago Ranco",13));
        db.insertarCiudad(new Ciudad(1,"Río Bueno",13));

        db.insertarCiudad(new Ciudad(1,"Puerto Montt",14));
        db.insertarCiudad(new Ciudad(1,"Calbuco",14));
        db.insertarCiudad(new Ciudad(1,"Cochamó",14));
        db.insertarCiudad(new Ciudad(1,"Fresia",14));
        db.insertarCiudad(new Ciudad(1,"Frutillar",14));
        db.insertarCiudad(new Ciudad(1,"Los Muermos",14));
        db.insertarCiudad(new Ciudad(1,"Llanquihue",14));
        db.insertarCiudad(new Ciudad(1,"Maullín",14));
        db.insertarCiudad(new Ciudad(1,"Puerto Varas",14));
        db.insertarCiudad(new Ciudad(1,"Castro",14));
        db.insertarCiudad(new Ciudad(1,"Ancud",14));
        db.insertarCiudad(new Ciudad(1,"Chonchi",14));
        db.insertarCiudad(new Ciudad(1,"Curaco de Vélez",14));
        db.insertarCiudad(new Ciudad(1,"Dalcahue",14));
        db.insertarCiudad(new Ciudad(1,"Puqueldón",14));
        db.insertarCiudad(new Ciudad(1,"Queilén",14));
        db.insertarCiudad(new Ciudad(1,"Quellón",14));
        db.insertarCiudad(new Ciudad(1,"Quemchi",14));
        db.insertarCiudad(new Ciudad(1,"Osorno",14));
        db.insertarCiudad(new Ciudad(1,"Puerto Octay",14));
        db.insertarCiudad(new Ciudad(1,"Purranque",14));
        db.insertarCiudad(new Ciudad(1,"Puyehue",14));
        db.insertarCiudad(new Ciudad(1,"Río Negro",14));
        db.insertarCiudad(new Ciudad(1,"San Juan de la Costa",14));
        db.insertarCiudad(new Ciudad(1,"San Pablo",14));
        db.insertarCiudad(new Ciudad(1,"Chaitén",14));
        db.insertarCiudad(new Ciudad(1,"Futaleufú",14));
        db.insertarCiudad(new Ciudad(1,"Hualaihué",14));
        db.insertarCiudad(new Ciudad(1,"Palena",14));

        db.insertarCiudad(new Ciudad(1,"Coyhaique",15));
        db.insertarCiudad(new Ciudad(1,"Lago Verde",15));
        db.insertarCiudad(new Ciudad(1,"Aysén",15));
        db.insertarCiudad(new Ciudad(1,"Cisnes",15));
        db.insertarCiudad(new Ciudad(1,"Guaitecas",15));
        db.insertarCiudad(new Ciudad(1,"Cochrane",15));
        db.insertarCiudad(new Ciudad(1,"O Higgins",15));
        db.insertarCiudad(new Ciudad(1,"Tortel",15));
        db.insertarCiudad(new Ciudad(1,"Chile Chico",15));
        db.insertarCiudad(new Ciudad(1,"Río Ibáñez",15));

        db.insertarCiudad(new Ciudad(1,"Punta Arenas",16));
        db.insertarCiudad(new Ciudad(1,"Laguna Blanca",16));
        db.insertarCiudad(new Ciudad(1,"Río Verde",16));
        db.insertarCiudad(new Ciudad(1,"San Gregorio",16));
        db.insertarCiudad(new Ciudad(1,"Cabo de Hornos",16));
        db.insertarCiudad(new Ciudad(1,"Antártica",16));
        db.insertarCiudad(new Ciudad(1,"Porvenir",16));
        db.insertarCiudad(new Ciudad(1,"Primavera",16));
        db.insertarCiudad(new Ciudad(1,"Timaukel",16));
        db.insertarCiudad(new Ciudad(1,"Natales",16));
        db.insertarCiudad(new Ciudad(1,"Torres del Paine",16));

        db.insertarCiudad(new Ciudad(1,"Santiago",17));
        db.insertarCiudad(new Ciudad(1,"Cerrillos",17));
        db.insertarCiudad(new Ciudad(1,"Cerro Navia",17));
        db.insertarCiudad(new Ciudad(1,"Conchalí",17));
        db.insertarCiudad(new Ciudad(1,"El Bosque",17));
        db.insertarCiudad(new Ciudad(1,"Estación Central",17));
        db.insertarCiudad(new Ciudad(1,"Huechuraba",17));
        db.insertarCiudad(new Ciudad(1,"Independencia",17));
        db.insertarCiudad(new Ciudad(1,"La Cisterna",17));
        db.insertarCiudad(new Ciudad(1,"La Florida",17));
        db.insertarCiudad(new Ciudad(1,"La Granja",17));
        db.insertarCiudad(new Ciudad(1,"La Pintana",17));
        db.insertarCiudad(new Ciudad(1,"La Reina",17));
        db.insertarCiudad(new Ciudad(1,"Las Condes",17));
        db.insertarCiudad(new Ciudad(1,"Lo Barnechea",17));
        db.insertarCiudad(new Ciudad(1,"Lo Espejo",17));
        db.insertarCiudad(new Ciudad(1,"Lo Prado",17));
        db.insertarCiudad(new Ciudad(1,"Macul",17));
        db.insertarCiudad(new Ciudad(1,"Maipú",17));
        db.insertarCiudad(new Ciudad(1,"Ñuñoa",17));
        db.insertarCiudad(new Ciudad(1,"Pedro Aguirre Cerda",17));
        db.insertarCiudad(new Ciudad(1,"Peñalolén",17));
        db.insertarCiudad(new Ciudad(1,"Providencia",17));
        db.insertarCiudad(new Ciudad(1,"Pudahuel",17));
        db.insertarCiudad(new Ciudad(1,"Quilicura",17));
        db.insertarCiudad(new Ciudad(1,"Quinta Normal",17));
        db.insertarCiudad(new Ciudad(1,"Recoleta",17));
        db.insertarCiudad(new Ciudad(1,"Renca",17));
        db.insertarCiudad(new Ciudad(1,"San Joaquín",17));
        db.insertarCiudad(new Ciudad(1,"San Miguel",17));
        db.insertarCiudad(new Ciudad(1,"San Ramón",177));
        db.insertarCiudad(new Ciudad(1,"Vitacura",17));
        db.insertarCiudad(new Ciudad(1,"Puente Alto",17));
        db.insertarCiudad(new Ciudad(1,"Pirque",17));
        db.insertarCiudad(new Ciudad(1,"San José de Maipo",17));
        db.insertarCiudad(new Ciudad(1,"Colina",17));
        db.insertarCiudad(new Ciudad(1,"Lampa",17));
        db.insertarCiudad(new Ciudad(1,"Til Til",17));
        db.insertarCiudad(new Ciudad(1,"San Bernardo",17));
        db.insertarCiudad(new Ciudad(1,"Buin",17));
        db.insertarCiudad(new Ciudad(1,"Calera de Tango",17));
        db.insertarCiudad(new Ciudad(1,"Paine",17));
        db.insertarCiudad(new Ciudad(1,"Melipilla",17));
        db.insertarCiudad(new Ciudad(1,"Alhué",17));
        db.insertarCiudad(new Ciudad(1,"Curacaví",17));
        db.insertarCiudad(new Ciudad(1,"María Pinto",17));
        db.insertarCiudad(new Ciudad(1,"San Pedro",17));
        db.insertarCiudad(new Ciudad(1,"Talagante",17));
        db.insertarCiudad(new Ciudad(1,"El Monte",17));
        db.insertarCiudad(new Ciudad(1,"Isla de Maipo",17));
        db.insertarCiudad(new Ciudad(1,"Padre Hurtado",17));
        db.insertarCiudad(new Ciudad(1,"Peñaflor",17));









        listaCiudades = db.getCiudades();
        /*
        for (Ciudad c: listaCiudades
             ) {
            System.out.println(c.getId());
            System.out.println(c.toString());
        }
        */

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




        db.insertarBiblioteca(new Biblioteca(1,"Biblioteca Municipal de Providencia", "Av Providencia 1590", "(2) 2236 0218",
                "biblioteca.providencia.cl ", 1, 317,-33.426977, -70.616802));


        db.insertarBiblioteca(new Biblioteca(1,"Café Literario Santa Isabel", "Santa Isabel 1240"
               , "(2) 2209 8964",
                "No se encontró", 1, 317,-33.446738, -70.614219));

        db.insertarBiblioteca(new Biblioteca(1,"Café Literario Parque Bustamante", "Metro Baquedano Providencia General Bustamante, Altura #50,", " (2) 2381 2230",
                "No se encontró", 1, 317,-33.439188, -70.632427));






        db.insertarBiblioteca(new Biblioteca(1,"Biblioteca San Bernardo", "Freire 473", "+56229270936",
                "biblioredes.cl", 1, 333,-33.592371, -70.703512));

        db.insertarBiblioteca(new Biblioteca(1,"Biblioteca Pública Municipal Nº 266", "José Joaquín, Pérez 547, Lebu, Bío Bío"
               , "No se encontró",
                "No tiene", 1, 333,-33.593892, -70.700819));

        db.insertarBiblioteca(new Biblioteca(1,"Libreria Bazar Amanda", "Freire 895", "+56982265914",
                "No tiene", 0, 333,-33.598553, -70.705948));





    }


}
