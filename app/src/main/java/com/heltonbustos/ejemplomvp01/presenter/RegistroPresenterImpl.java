package com.heltonbustos.ejemplomvp01.presenter;

import android.content.Context;

import com.heltonbustos.ejemplomvp01.interactor.LoginInteractorImpl;
import com.heltonbustos.ejemplomvp01.interactor.RegistroInteractorImpl;
import com.heltonbustos.ejemplomvp01.interfaces.LoginInteractor;
import com.heltonbustos.ejemplomvp01.interfaces.LoginView;
import com.heltonbustos.ejemplomvp01.interfaces.RegistroInteractor;
import com.heltonbustos.ejemplomvp01.interfaces.RegistroPresenter;
import com.heltonbustos.ejemplomvp01.interfaces.RegistroView;
import com.heltonbustos.ejemplomvp01.view.Registro;

public class RegistroPresenterImpl implements RegistroPresenter {

    RegistroView vista;
    RegistroInteractor interactor;

    public RegistroPresenterImpl(RegistroView vista) {
        this.vista = vista;
        interactor = new RegistroInteractorImpl();
    }

    @Override
    public void registrar(String nombre, String user, String pass, Context contexto) {
        interactor.registrar(nombre, user, pass, this, contexto);
    }

    @Override
    public void error() {
        vista.error();
    }

    @Override
    public void exito() {
        vista.exito();
    }

    @Override
    public void setErrorNombre() {
        vista.setErrorNombre();
    }

    @Override
    public void setErrorUser() {
        vista.setErrorUser();
    }

    @Override
    public void setErrorPassword() {
        vista.setErrorPassword();
    }
}
