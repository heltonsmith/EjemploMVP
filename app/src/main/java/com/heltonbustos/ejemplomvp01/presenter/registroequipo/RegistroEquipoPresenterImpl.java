package com.heltonbustos.ejemplomvp01.presenter.registroequipo;

import android.content.Context;

import com.heltonbustos.ejemplomvp01.interactor.registroequipo.RegistroEquipoInteractorImpl;
import com.heltonbustos.ejemplomvp01.interfaces.registroequipo.RegistroEquipoInteractor;
import com.heltonbustos.ejemplomvp01.interfaces.registroequipo.RegistroEquipoPresenter;
import com.heltonbustos.ejemplomvp01.interfaces.registroequipo.RegistroEquipoView;

public class RegistroEquipoPresenterImpl implements RegistroEquipoPresenter {
    RegistroEquipoView vista;
    RegistroEquipoInteractor interactor;

    public RegistroEquipoPresenterImpl(RegistroEquipoView vista) {
        this.vista = vista;
        this.interactor = new RegistroEquipoInteractorImpl();
    }

    @Override
    public void registrar(String codigo, String nombre, String fecha, String bolso, String cargador, Context contexto) {
        interactor.registrar(codigo, nombre, fecha, bolso, cargador, this, contexto);
    }

    @Override
    public void exito() {
        vista.exito();
    }

    @Override
    public void error() {
        vista.error();
    }

    @Override
    public void setErrorCodigo() {
        vista.setErrorCodigo();
    }

    @Override
    public void setErrorNombre() {
        vista.setErrorNombre();
    }
}
