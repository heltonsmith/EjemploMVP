package com.heltonbustos.ejemplomvp01.interfaces.loginuser;

import android.content.Context;

public interface LoginInteractor {
    void validarUsuario(String user, String pass, LoginPresenter presentador, Context contexto);
}
