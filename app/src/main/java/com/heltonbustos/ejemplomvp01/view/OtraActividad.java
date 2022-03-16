package com.heltonbustos.ejemplomvp01.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.heltonbustos.ejemplomvp01.R;

public class OtraActividad extends AppCompatActivity {

    TextView txtNombre2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        txtNombre2 = findViewById(R.id.txtNombre2);

        String nombre = getIntent().getStringExtra("x");

        txtNombre2.setText("Bienvenido: " + nombre);
    }
}