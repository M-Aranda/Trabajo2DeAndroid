package com.arandastock001.arandabibliotecas.Modelo.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.arandastock001.arandabibliotecas.Modelo.Biblioteca;
import com.arandastock001.arandabibliotecas.Modelo.Ciudad;
import com.arandastock001.arandabibliotecas.Modelo.Region;

import java.util.ArrayList;
import java.util.List;


public class Data extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "BD_APP_BIBLIOTECAS";
    private static final int VERSION_BD = 1;
    private static final String TABLA_REGION = "CREATE TABLE REGION(_id INTEGER PRIMARY KEY AUTOINCREMENT, _nombre TEXT);";
    private static final String TABLA_CIUDAD = "CREATE TABLE CIUDAD(_id INTEGER PRIMARY KEY AUTOINCREMENT, _nombre TEXT, _fkRegion INT, FOREIGN KEY (_fkRegion) REFERENCES REGION (_id));";
    private static final String TABLA_BIBLIOTECA = "CREATE TABLE BIBLIOTECA(_id INTEGER PRIMARY KEY AUTOINCREMENT, _nombre TEXT, _direccion TEXT, _telefono TEXT, _sitioWeb TEXT, _esPublica BOOLEAN, _fkCiudad INT, FOREIGN KEY (_fkCiudad) REFERENCES CIUDAD (_id));";

    public Data(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_REGION);
        db.execSQL(TABLA_CIUDAD);
        db.execSQL(TABLA_BIBLIOTECA);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrade");
    }


    public void reiniciar(){
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("DROP TABLE IF  EXISTS REGION;");
        bd.execSQL("DROP TABLE IF  EXISTS CIUDAD;");
        bd.execSQL("DROP TABLE IF  EXISTS BIBLIOTECA;");

        bd.execSQL(TABLA_REGION);
        bd.execSQL(TABLA_CIUDAD);
        bd.execSQL(TABLA_BIBLIOTECA);
    }



    public void insertarRegion (Region r){
        SQLiteDatabase bd = getWritableDatabase();

        if (bd != null){
            String query="INSERT INTO REGION VALUES (NULL, '"+r.getNombre()+"');";
            bd.execSQL("INSERT INTO REGION VALUES (NULL, '"+r.getNombre()+"');");

            System.out.println(query);
        }else{
            System.out.println("No existe bd");
        }
        bd.close();
    }


    public void insertarCiudad (Ciudad c){
        SQLiteDatabase bd = getWritableDatabase();

        if (bd != null){
            String query="INSERT INTO CIUDAD VALUES (NULL, '"+c.getNombre()+"', "+c.getFkRegion()+");";
            bd.execSQL(query);

            System.out.println(query);
        }else{
            System.out.println("No existe bd");
        }
        bd.close();
    }

    public List<Region> getRegiones(){
        List<Region> regiones = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM REGION; ",null);

        Region r;
        while(leer.moveToNext()){
            r = new Region();
            r.setId(leer.getInt(0));
            r.setNombre(leer.getString(1));
            System.out.print(String.valueOf(r.getId()));
            System.out.println(" "+r.getNombre());
            regiones.add(r);

        }

        bd.close();

          return regiones;
    }



    public List<Ciudad> getCiudades(){
        List<Ciudad> ciudades =new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM CIUDAD; ",null);

        Ciudad c = new Ciudad();
        while(leer.moveToNext()){
            c = new Ciudad();
            c.setId(leer.getInt(0));
            c.setNombre(leer.getString(1));
            c.setFkRegion(leer.getInt(2));
            ciudades.add(c);
        }
        bd.close();

        return ciudades;
    }



    public List<Biblioteca> getBiblitecas(){
        List<Biblioteca> bibiliotecas =new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM BIBLIOTECA; ",null);

        Biblioteca b;
        while(leer.moveToNext()){
            b = new Biblioteca();
            b.setId(leer.getInt(0));
            b.setNombre(leer.getString(1));
            b.setDireccion(leer.getString(2));
            b.setTelefono(leer.getString(3));
            b.setSitioWeb(leer.getString(4));
            b.setEsPublica((leer.getInt(5)>1));
            b.setFkCiudad(leer.getInt(6));
            bibiliotecas.add(b);
        }
        bd.close();

        return bibiliotecas;
    }

    public List<Biblioteca> getBiblitecasDeCiudadPorTipo(int id, Boolean esPublica){
        List<Biblioteca> bibiliotecas =new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM BIBLIOTECA WHERE _fkCiudad = "+id+", _esPublica = "+esPublica+" AND _fkCiudad != 1;" ,null);

        Biblioteca b;
        while(leer.moveToNext()){
            b = new Biblioteca();
            b.setId(leer.getInt(0));
            b.setNombre(leer.getString(1));
            b.setDireccion(leer.getString(2));
            b.setTelefono(leer.getString(3));
            b.setSitioWeb(leer.getString(4));
            b.setEsPublica((leer.getInt(5)>1));
            b.setFkCiudad(leer.getInt(6));
            bibiliotecas.add(b);
        }
        bd.close();

        return bibiliotecas;
    }







    public List<Ciudad> getCiudadesDeLaRegion(int fkRegion){
        List<Ciudad> ciudades =new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM CIUDAD WHERE _fkRegion= "+fkRegion+";",null);

        Ciudad c;
        while (leer.moveToNext()){
            c = new Ciudad();
            c.setId(leer.getInt(0));
            c.setNombre(leer.getString(1));
            c.setFkRegion(leer.getInt(2));
            ciudades.add(c);
        }
        bd.close();

        return ciudades;
    }


    public List<Biblioteca> getBibliotecasFiltradas(String fkCiudad, int esPublica){
        List<Biblioteca> bibiliotecas =new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        Cursor leer = bd.rawQuery("SELECT * FROM BIBILIOTECA WHERE _fkCiudad= "+fkCiudad+" AND _esPublica= "+esPublica+" ",null);

        Biblioteca b = new Biblioteca();
        while(leer.moveToNext()){
            b.setId(leer.getInt(0));
            b.setNombre(leer.getString(1));
            b.setDireccion(leer.getString(2));
            b.setTelefono(leer.getString(3));
            b.setSitioWeb(leer.getString(4));
            b.setEsPublica((leer.getInt(5)>1));
            b.setFkCiudad(leer.getInt(6));
            bibiliotecas.add(b);
        }
        bd.close();

        return bibiliotecas;
    }




}
