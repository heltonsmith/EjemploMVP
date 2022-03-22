package com.heltonbustos.ejemplomvp01.interfaces.verregistros;

import androidx.recyclerview.widget.RecyclerView;

import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;

import java.util.ArrayList;

public interface FragVerEquiposViewInterface {
    void exito(ArrayList<RegistroEquiposDatos> listaRegistros);
    void error();
}
