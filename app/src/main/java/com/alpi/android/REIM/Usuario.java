package com.alpi.android.REIM;

public class Usuario {

    private int idUsuario;
    private String rut;
    private String nombreUsuario;
    private String apellidoPaterno;

    //Constructor vacío
    public Usuario() {

    }

    //Constructor de la clase
    public Usuario(int idUsuario, String nombreUsuario, String rut, String apellidoPaterno) {
        this.idUsuario = idUsuario;
        this.rut = rut;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
    }

    public Usuario(String nombreUsuario, String rut, String apellidoPaterno) {
        this.rut = rut;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", rut='" + rut + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                '}';
    }
}
