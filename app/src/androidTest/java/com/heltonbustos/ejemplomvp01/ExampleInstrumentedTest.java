package com.heltonbustos.ejemplomvp01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;
import com.heltonbustos.ejemplomvp01.interactor.test.RegistroEquipoModelo;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.heltonbustos.ejemplomvp01", appContext.getPackageName());
    }

    @Test
    public void probarConsultaLoigin(){
        String user = "1";
        String pass = "1";
        String nombre = "";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ConexionBD conexion = new ConexionBD(appContext, "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT nombre FROM usuarios WHERE user='"+user+"' and pass='"+pass+"'", null);

        if(fila.moveToFirst()){
            nombre = fila.getString(0);
            bd.close();
        }
        else{
            bd.close();
        }

        assertEquals("helton", nombre);

    }


}