package com.heltonbustos.ejemplomvp01.interfaces.loginuser;

import android.content.Context;

public interface LoginPresenter {

    void validarUsuario(String user, String pass, Context contexto);
    void setErrorUser();
    void setErrorPassword();
    void exito(String nombre);
    void noExiste();

}
