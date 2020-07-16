package com.arandastock001.arandabibliotecas;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionDeLaBiblioteca extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Biblioteca b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_de_la_biblioteca);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }

    public void init(){
        Intent i = getIntent();

        b = ((Biblioteca) i.getSerializableExtra("bibliotecaSeleccionada"));
        System.out.println("El nombre de la bibliotecas es"+b.getNombre());


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
                startActivity(new Intent(UbicacionDeLaBiblioteca.this, BuscadorDeBibiliotecas.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        //La primera es latitud, la segunda es longitud
        //LatLng posicion = new LatLng(-18.481431, -70.319800);
        LatLng posicion = new LatLng(b.getLatitud(), b.getLongitud());
        mMap.addMarker(new MarkerOptions().position(posicion).title("Biblioteca "+b.getNombre()));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 30));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



    }
}
