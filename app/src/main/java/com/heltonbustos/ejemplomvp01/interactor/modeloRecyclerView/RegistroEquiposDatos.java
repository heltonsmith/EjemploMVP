package com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView;

import android.graphics.Bitmap;

public class RegistroEquiposDatos {

    private Bitmap b1;
    private Bitmap b2;
    private String codigo;
    private String nombre;
    private String fecha;
    private String bolso;
    private String cargador;

    public RegistroEquiposDatos(Bitmap b1, Bitmap b2, String codigo, String nombre, String fecha, String bolso, String cargador) {
        this.b1 = b1;
        this.b2 = b2;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.bolso = bolso;
        this.cargador = cargador;
    }

    public Bitmap getB1() {
        return b1;
    }

    public void setB1(Bitmap b1) {
        this.b1 = b1;
    }

    public Bitmap getB2() {
        return b2;
    }

    public void setB2(Bitmap b2) {
        this.b2 = b2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getBolso() {
        return bolso;
    }

    public void setBolso(String bolso) {
        this.bolso = bolso;
    }

    public String getCargador() {
        return cargador;
    }

    public void setCargador(String cargador) {
        this.cargador = cargador;
    }
}
