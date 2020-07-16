package com.arandastock001.arandabibliotecas.Modelo;

import java.io.Serializable;

public class Biblioteca implements Serializable {

    private int id;
    private String nombre, direccion, telefono, sitioWeb;
    private int esPublica, fkCiudad;
    private Double latitud, longitud;

    public Biblioteca() {
    }

    public Biblioteca(int id, String nombre, String direccion, String telefono, String sitioWeb, int esPublica, int fkCiudad, Double latitud, Double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.sitioWeb = sitioWeb;
        this.esPublica = esPublica;
        this.fkCiudad = fkCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public int getEsPublica() {
        return esPublica;
    }

    public void setEsPublica(int esPublica) {
        this.esPublica = esPublica;
    }

    public int getFkCiudad() {
        return fkCiudad;
    }

    public void setFkCiudad(int fkCiudad) {
        this.fkCiudad = fkCiudad;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        String tipo="privada";
        if(this.getEsPublica()==1){
            tipo="pública";
        }
        return "Biblioteca "+this.getNombre()+" \n" +
                "Biblioteca "+tipo;
    }
}
