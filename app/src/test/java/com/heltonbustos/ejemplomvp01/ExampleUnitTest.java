package com.heltonbustos.ejemplomvp01;

import org.junit.Test;

import static org.junit.Assert.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heltonbustos.ejemplomvp01.interactor.bd.ConexionBD;
import com.heltonbustos.ejemplomvp01.interactor.test.RegistroEquipoModelo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 3 + 2);
    }

}