package com.heltonbustos.ejemplomvp01.interfaces.registrouser;

import android.content.Context;

public interface RegistroPresenter {
    void registrar(String nombre, String user, String pass, Context contexto);
    void error();
    void exito();
    void setErrorNombre();
    void setErrorUser();
    void setErrorPassword();
}
