package com.heltonbustos.ejemplomvp01.interactor.verregistros;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heltonbustos.ejemplomvp01.R;
import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.AdaptadorDatos;
import com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView.RegistroEquiposDatos;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposInteractorInterface;
import com.heltonbustos.ejemplomvp01.interfaces.verregistros.FragVerEquiposPresentadorInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragVerEquiposInteractorImpl implements FragVerEquiposInteractorInterface {

    ArrayList<RegistroEquiposDatos> listaRegistros = new ArrayList<>();

    @Override
    public void llenarRecycler(FragVerEquiposPresentadorInterface presentador, Context contexto) {

        File ruta = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/MyApp/");
        }
        else{
            ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }

        File fotos[] = ruta.listFiles();

        String codigo = "";
        String nombre = "";
        String fecha = "";
        String bolso = "";
        String cargador = "";

        ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
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
                    Toast.makeText(contexto, "Aun no hay fotos", Toast.LENGTH_SHORT).show();
                    break;
                }
            } while(fila.moveToNext());

            fila.close();
            bd.close();



            presentador.exito(listaRegistros);
        }
        else{
            presentador.error();
        }

    }
}
