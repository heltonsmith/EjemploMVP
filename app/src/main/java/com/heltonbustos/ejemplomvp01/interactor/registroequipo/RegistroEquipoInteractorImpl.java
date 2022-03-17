package com.heltonbustos.ejemplomvp01.interactor.registroequipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;
import com.heltonbustos.ejemplomvp01.interfaces.registroequipo.RegistroEquipoInteractor;
import com.heltonbustos.ejemplomvp01.presenter.registroequipo.RegistroEquipoPresenterImpl;

public class RegistroEquipoInteractorImpl implements RegistroEquipoInteractor {
    @Override
    public void registrar(String codigo, String nombre, String fecha,
                          String bolso, String cargador, String foto1, String foto2, RegistroEquipoPresenterImpl presenter, Context contexto) {
        if(codigo.equals("")){
            presenter.setErrorCodigo();
        }
        else if(nombre.equals("")){
            presenter.setErrorNombre();
        }
        else {
            //registrar en SQLite
            ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
            SQLiteDatabase bd = conexion.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("fecha", fecha);
            registro.put("bolso", bolso);
            registro.put("cargador", cargador);
            registro.put("foto1", foto1);
            registro.put("foto2", foto2);

            long x = bd.insert("registroequipo", null, registro);
            bd.close();

            if (x > 0) {
                presenter.exito();
            } else {
                presenter.error();
            }
        }
    }
}
