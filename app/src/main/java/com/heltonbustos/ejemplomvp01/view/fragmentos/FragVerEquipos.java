package com.heltonbustos.ejemplomvp01.view.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.heltonbustos.ejemplomvp01.R;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposPresentadorInterface;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposViewInterface;
import com.heltonbustos.ejemplomvp01.presenter.verregistros.FragVerEquiposPresentadorImpl;

import java.util.ArrayList;

public class FragVerEquipos extends Fragment implements FragVerEquiposViewInterface {

    AdaptadorDatos adaptador;
    FragVerEquiposPresentadorInterface presentador;
    RecyclerView myRecyclerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ver_equipos, container, false);

        myRecyclerView2 = v.findViewById(R.id.myRecyclerView2);

        presentador = new FragVerEquiposPresentadorImpl(this);
        presentador.llenarRecycler(getContext());

        return v;
    }

    @Override
    public void exito(ArrayList<RegistroEquiposDatos> listaRegistros) {
        /*
        adaptador = new AdaptadorDatos(listaRegistros, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myRecyclerView2.setLayoutManager(layoutManager);
        myRecyclerView2.setAdapter(adaptador);
        */

        myRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView2.setAdapter(new AdaptadorDatos(listaRegistros, getContext()));
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Aun no hay Registros  ", Toast.LENGTH_SHORT).show();
    }
}