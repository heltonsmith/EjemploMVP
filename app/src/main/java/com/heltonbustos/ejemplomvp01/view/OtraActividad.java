package com.heltonbustos.ejemplomvp01.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.heltonbustos.ejemplomvp01.R;

import org.w3c.dom.Text;

public class OtraActividad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout myDrawer;
    NavigationView myNav;
    Toolbar myToolbar;

    ActionBarDrawerToggle toogle; //para implementar el icono de hamburguesa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        myDrawer = findViewById(R.id.myDrawerLayout);
        myNav = findViewById(R.id.myNavigationView);
        myToolbar = findViewById(R.id.myToolbar);

        String nombre = getIntent().getStringExtra("x");

        //mostrar actionbar
        setSupportActionBar(myToolbar);

        //eventos click en items de navigationDrawer
        myNav.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myFrame, new FragRegistroEquipo())
                .commit();
        setTitle("Registrar equipo " + nombre);

        //para activar icono hamburguesa
        //toogle = new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
        toogle = setDrawerToogle();
        myDrawer.addDrawerListener(toogle); //para oir al icono de hamburguesa

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }

    private ActionBarDrawerToggle setDrawerToogle() {
        return new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //para mostrar los fragmentos
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_registro:
                ft.replace(R.id.myFrame, new FragRegistroEquipo()).commit();
                break;
            case R.id.nav_ver:
                ft.replace(R.id.myFrame, new FragVerEquipos()).commit();
                break;
            case R.id.nav_eliminar:
                ft.replace(R.id.myFrame, new FragEliminarRegistroEquipos()).commit();
                break;
        }
        setTitle(item.getTitle()); //para mostrar el título
        myDrawer.closeDrawers(); //para cerrar drawer
        return true;
    }
}