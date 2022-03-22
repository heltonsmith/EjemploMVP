package com.heltonbustos.ejemplomvp01.presenter.verregistros;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;
import com.heltonbustos.ejemplomvp01.interactor.verregistros.FragVerEquiposInteractorImpl;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposInteractorInterface;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposPresentadorInterface;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposViewInterface;

import java.util.ArrayList;

public class FragVerEquiposPresentadorImpl implements FragVerEquiposPresentadorInterface {

    FragVerEquiposViewInterface vista;
    FragVerEquiposInteractorInterface interactor;

    public FragVerEquiposPresentadorImpl(FragVerEquiposViewInterface vista) {
        this.interactor = new FragVerEquiposInteractorImpl();
        this.vista = vista;
    }

    @Override
    public void llenarRecycler(Context contexto) {
        interactor.llenarRecycler(this, contexto);
    }

    @Override
    public void exito(ArrayList<RegistroEquiposDatos> listaRegistros) {
        vista.exito(listaRegistros);
    }

    @Override
    public void error() {
        vista.error();
    }
}
