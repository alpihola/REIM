package com.alpi.android.REIM;

public class Usuario {

    int _id;
    String nombreUsuario;
    String rut;

    //Constructor vacío
    public Usuario() {

    }

    //Constructor de la clase
    public Usuario(int id, String nombreUsuario, String rut) {
        this._id = id;
        this.nombreUsuario = nombreUsuario;
        this.rut = rut;
    }

    public Usuario(String nombreUsuario, String rut) {
        this.nombreUsuario = nombreUsuario;
        this.rut = rut;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

}
