package com.heltonbustos.ejemplomvp01.interfaces.verregistros;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;

import java.util.ArrayList;

public interface FragVerEquiposPresentadorInterface {
    void llenarRecycler(Context contexto);
    void exito(ArrayList<RegistroEquiposDatos> listaRegistros);
    void error();
}
