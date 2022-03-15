package com.heltonbustos.ejemplomvp01.interfaces;

public interface LoginInteractor {
    void validarUsuario(String user, String pass, LoginPresenter presentador);
}
