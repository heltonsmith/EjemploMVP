package com.heltonbustos.ejemplomvp01.view.fragmentos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.heltonbustos.ejemplomvp01.R;
import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;
import com.heltonbustos.ejemplomvp01.interactor.test.RegistroEquipoModelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragVerEquipos extends Fragment {

    RecyclerView myRecyclerView2;
    AdaptadorDatos adaptador;
    ArrayList<RegistroEquiposDatos> listaRegistros = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ver_equipos, container, false);

        File ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File fotos[] = ruta.listFiles();

        String codigo = "";
        String nombre = "";
        String fecha = "";
        String bolso = "";
        String cargador = "";

        ConexionBD conexion = new ConexionBD(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM registroequipo ORDER BY fecha DESC", null);

        if (fila.moveToFirst()){
            do {
                codigo = fila.getString(0);
                nombre = fila.getString(1);
                fecha = fila.getString(2);
                bolso = fila.getString(3);
                cargador = fila.getString(4);

                List<Bitmap> archivos = new ArrayList<>();

                if(fotos != null) {
                    for (int i = 0; i < fotos.length; i++) {
                        if (fotos[i].getAbsolutePath().contains(codigo)) {
                            archivos.add(BitmapFactory.decodeFile(fotos[i].getAbsolutePath()));
                        }
                    }

                    listaRegistros.add(
                            new RegistroEquiposDatos(archivos.get(0), archivos.get(1),
                                    "Código: " + codigo, "Nombre: " + nombre, "Fecha: " + fecha, "Bolso: " + bolso, "Cargador: " + cargador));
                }
                else{
                    Toast.makeText(getContext(), "Aun no hay fotos", Toast.LENGTH_SHORT).show();
                    break;
                }
            } while(fila.moveToNext());

            myRecyclerView2 = v.findViewById(R.id.myRecyclerView2);
            adaptador = new AdaptadorDatos(listaRegistros, getContext());

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            myRecyclerView2.setLayoutManager(layoutManager);

            myRecyclerView2.setAdapter(adaptador);
        }
        else{
            Toast.makeText(getContext(), "Aun no hay Registros  ", Toast.LENGTH_SHORT).show();
        }

        fila.close();
        bd.close();

        return v;
    }
}