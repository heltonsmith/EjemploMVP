package com.heltonbustos.ejemplomvp01.interactor.test;

public class RegistroEquipoModelo {

    private String codigo;
    private String nombre;
    private String fecha;
    private String bolso;
    private String cargador;

    public RegistroEquipoModelo(String codigo, String nombre, String fecha, String bolso, String cargador) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.bolso = bolso;
        this.cargador = cargador;

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
