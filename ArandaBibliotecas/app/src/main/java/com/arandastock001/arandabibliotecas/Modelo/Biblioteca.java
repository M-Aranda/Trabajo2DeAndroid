package com.arandastock001.arandabibliotecas.Modelo;

public class Biblioteca {

    private int id;
    private String nombre, direccion, telefono, sitioWeb;
    private Boolean esPublica;
    private int fkCiudad;

    public Biblioteca() {
    }

    public Biblioteca(int id, String nombre, String direccion, String telefono, String sitioWeb, Boolean esPublica, int fkCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.sitioWeb = sitioWeb;
        this.esPublica = esPublica;
        this.fkCiudad = fkCiudad;
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

    public Boolean getEsPublica() {
        return esPublica;
    }

    public void setEsPublica(Boolean esPublica) {
        this.esPublica = esPublica;
    }

    public int getFkCiudad() {
        return fkCiudad;
    }

    public void setFkCiudad(int fkCiudad) {
        this.fkCiudad = fkCiudad;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", sitioWeb='" + sitioWeb + '\'' +
                ", esPublica=" + esPublica +
                ", fkCiudad=" + fkCiudad +
                '}';
    }
}