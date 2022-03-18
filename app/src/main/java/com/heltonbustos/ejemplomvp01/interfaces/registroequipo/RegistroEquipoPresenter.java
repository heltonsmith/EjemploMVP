package com.heltonbustos.ejemplomvp01.interfaces.registroequipo;

import android.content.Context;

public interface RegistroEquipoPresenter {
    void registrar(String codigo, String nombre, String fecha,
                   String bolso, String cargador, Context contexto);
    void exito();
    void error();
    void setErrorCodigo();
    void setErrorNombre();
}
