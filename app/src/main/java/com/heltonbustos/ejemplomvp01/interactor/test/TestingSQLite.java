package com.heltonbustos.ejemplomvp01.interactor.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;

import java.util.ArrayList;
import java.util.List;

public class TestingSQLite {

    Context contexto;
    List<RegistroEquipoModelo> lista = new ArrayList<>();

    public TestingSQLite(Context contexto) {
        this.contexto = contexto;
    }

    public void listarTodo(){
        String codigo = "";
        String nombre = "";
        String fecha = "";
        String bolso = "";
        String cargador = "";

        ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM registroequipo", null);

        if (fila.moveToFirst()){
            do {
                codigo = fila.getString(0);
                nombre = fila.getString(1);
                fecha = fila.getString(2);
                bolso = fila.getString(3);
                cargador = fila.getString(4);

                lista.add(new RegistroEquipoModelo(codigo, nombre, fecha, bolso, cargador));
            } while(fila.moveToNext());
        }
        fila.close();
        bd.close();
    }

}
