package com.heltonbustos.ejemplomvp01.interactor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.Toast;

import com.heltonbustos.ejemplomvp01.interfaces.LoginInteractor;
import com.heltonbustos.ejemplomvp01.interfaces.LoginPresenter;

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void validarUsuario(String user, String pass, LoginPresenter presentador, Context contexto) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user.equals("")){
                    presentador.setErrorUser();
                }
                else if(pass.equals("")){
                    presentador.setErrorPassword();
                }
                else{
                    ConexionBD conexion = new ConexionBD(contexto, "administracion", null, 1);
                    SQLiteDatabase bd = conexion.getWritableDatabase();

                    Cursor fila = bd.rawQuery("SELECT nombre FROM usuarios WHERE user='"+user+"' and pass='"+pass+"'", null);

                    if(fila.moveToFirst()){
                        presentador.exito();
                        bd.close();
                    }
                    else{
                        presentador.noExiste();
                        bd.close();
                    }
                }
            }
        }, 500);

    }
}
