package com.alpi.android.REIM;

public class Alimento {

    private String nombreAlimento;
    private String imagenAlimento;
    private boolean correspondeAlimento;

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public String getImagenAlimento() {
        return imagenAlimento;
    }

    public void setImagenAlimento(String imagenAlimento) {
        this.imagenAlimento = imagenAlimento;
    }

    public boolean getCorrespondeAlimento() {
        return correspondeAlimento;
    }

    public void setCorrespondeAlimento(boolean corresponde) {
        this.correspondeAlimento = corresponde;
    }

}
