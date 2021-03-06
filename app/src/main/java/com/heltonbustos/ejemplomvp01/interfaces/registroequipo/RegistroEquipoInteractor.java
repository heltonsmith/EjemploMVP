package com.heltonbustos.ejemplomvp01.interfaces.registroequipo;
import android.content.Context;

import com.heltonbustos.ejemplomvp01.presenter.registroequipo.RegistroEquipoPresenterImpl;

public interface RegistroEquipoInteractor {
    void registrar(String codigo, String nombre,
                   String fecha, String bolso, String cargador,
                   RegistroEquipoPresenterImpl presenter, Context contexto);
}
