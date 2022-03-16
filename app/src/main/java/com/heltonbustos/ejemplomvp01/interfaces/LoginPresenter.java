package com.heltonbustos.ejemplomvp01.interfaces;

import android.content.Context;

public interface LoginPresenter {

    void validarUsuario(String user, String pass, Context contexto);
    void setErrorUser();
    void setErrorPassword();
    void exito();
    void noExiste();

}
