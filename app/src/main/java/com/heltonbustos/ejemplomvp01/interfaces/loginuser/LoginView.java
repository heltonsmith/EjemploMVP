package com.heltonbustos.ejemplomvp01.interfaces.loginuser;

public interface LoginView {

    void mostrarProgreso();
    void esconderProgreso();
    void setErrorUser();
    void setErrorPassword();
    void exito(String nombre);
    void noExiste();

}
