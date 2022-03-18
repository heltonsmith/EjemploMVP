package com.heltonbustos.ejemplomvp01.interactor.manejocamara;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class Permisos {

    Context contexto;
    Activity actividad;
    private static final int REQUEST_PERMISSION_CAMERA = 100; //detectar la respuesta del usuario si es OK

    public Permisos(Context contexto, Activity actividad) {
        this.actividad = actividad;
        this.contexto = contexto;
    }

    public int permisoGeneral(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //android marshmallow (Permiso en tiempo de ejecución)
            if (ActivityCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                return 1;
            }
            else{ //api > 28 (Q)
                ActivityCompat.requestPermissions(
                        actividad,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
            }
        }
        else{ //permiso en tiempo de descarga
            return 1;
        }

        return 0;
    }

}
