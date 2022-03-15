package com.heltonbustos.ejemplomvp01.interfaces;

public interface LoginPresenter {

    void validarUsuario(String user, String pass);
    void setErrorUser();
    void setErrorPassword();
    void exito();
    void noExiste();

}
