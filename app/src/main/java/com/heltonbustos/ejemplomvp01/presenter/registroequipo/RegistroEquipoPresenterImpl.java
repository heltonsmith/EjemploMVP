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
    public void registrar(String codigo, String nombre, String fecha, String bolso, String cargador, String foto1, String foto2, Context contexto) {
        interactor.registrar(codigo, nombre, fecha, bolso, cargador, foto1, foto2, this, contexto);
    }

    @Override
    public void exito() {
        vista.error();
    }

    @Override
    public void error() {
        vista.exito();
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
