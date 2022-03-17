package com.heltonbustos.ejemplomvp01.view.actividades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.heltonbustos.ejemplomvp01.R;
import com.heltonbustos.ejemplomvp01.view.fragmentos.FragEliminarRegistroEquipos;
import com.heltonbustos.ejemplomvp01.view.fragmentos.FragRegistroEquipo;
import com.heltonbustos.ejemplomvp01.view.fragmentos.FragVerEquipos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OtraActividad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * variables para manejar permisos y respuestas
     */
    private static final int REQUEST_PERMISSION_CAMERA = 100; //detectar la respuesta del usuario si es OK
    private static final int TAKE_PICTURE = 101; //detecta si se tomo la foto con la camara del celular
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200; //detectar la respuesta del usuario si es ok

    /**
     * elementos para implementar NavigationDrawer
     */
    DrawerLayout myDrawer;
    NavigationView myNav;
    Toolbar myToolbar;
    ActionBarDrawerToggle toogle; //para implementar el icono de hamburguesa

    //variables para cambiarle el nombre a la foto
    String foto1 = "";
    String foto2 = "";

    ImageView imActual;

    Bitmap bitmap;
    List<Bitmap> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        //vistas para implementar drawer
        myDrawer = findViewById(R.id.myDrawerLayout);
        myNav = findViewById(R.id.myNavigationView);
        myToolbar = findViewById(R.id.myToolbar);

        //variable que viene de actividad anterior
        String nombre = getIntent().getStringExtra("x");

        //mostrar actionbar
        setSupportActionBar(myToolbar);

        //eventos click en items de navigationDrawer
        myNav.setNavigationItemSelectedListener(this);

        //para iniciar fragmento
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

    /*
     * implementacion de Camara permisos permisosCamara1() y permisoCamaraGeneral()
     */
    public void permisosCamara1(String nombreFoto, ImageView ima){
        this.foto1 = nombreFoto;
        this.imActual = ima;
        permisoCamaraGeneral();
    }

    public void permisosCamara2(String nombreFoto, ImageView ima){
        this.foto2 = nombreFoto;
        this.imActual = ima;
        permisoCamaraGeneral();
    }

    public void permisoCamaraGeneral(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //android marshmallow (Permiso en tiempo de ejecución)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            }
            else{ //api > 28 (Q)
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
            }
        }
        else{ //permiso en tiempo de descarga
            tomarFoto();
        }
    }
    /*
     * implementacion de Camara permisos permisosCamara1() y permisoCamaraGeneral()
     */

    /*
     * tomar foto (abre intent implicito camara)
     */
    public void tomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, TAKE_PICTURE);
        }
    }
    /*
     * tomar foto (abre intent implicito camara)
     */

    /*
     * implementacion de respuestas al sacar la foto
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if (resultCode == Activity.RESULT_OK && data != null){
                listaFotos.add((Bitmap) data.getExtras().get("data"));
                bitmap = (Bitmap) data.getExtras().get("data");
                //invocamos al fragmento para mostrar imagen en su respectivo ImageView
                FragRegistroEquipo.mostrarImagen(this.imActual, bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /*
     * implementacion de respuestas al sacar la foto
     */


    /*
     * implementacion permisos de almacenamiento y guardado de fotos permisosAlmacenamiento1()
     */
    public void permisosAlmacenamiento(){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){ //Apis mas antiguas < 28
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    guardarFoto1();
                    guardarFoto2();
                }
                else{
                    //api < 28 (Q)
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }
            }
            else{
                guardarFoto1();
                guardarFoto2();
            }
        }
        else{
            guardarFoto1();
            guardarFoto2();
        }
    }


    /**
     * Método para almacenar foto fisicamente
     */
    public void guardarFoto1(){ //Android Q y posteriores
        OutputStream outputStream = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){ //versiones recientes
            ContentResolver resolver = getContentResolver(); //para manejar los values
            ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = "foto1" + "@" + tiempo;

            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
            Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


            try {
                outputStream = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imageUri, values, null, null);
        }
        else{ //Apis mas antiguas < 28
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = "foto1" + "@" + tiempo + ".jpg"; //nombre del archivo

            file = new File(imageDir, filename);

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        boolean saved = listaFotos.get(0).compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //para la calidad y compresión del archivo
        if (saved){
            Toast.makeText(this, "Imagen registrada: " + "nombreFoto", Toast.LENGTH_SHORT).show();
        }

        if (outputStream != null){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (file != null){
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
        }

    }
    /*
     * implementacion permisos de almacenamiento y guardado de fotos
     */


    /**
     * Método para almacenar foto fisicamente
     */
    public void guardarFoto2(){ //Android Q y posteriores
        OutputStream outputStream = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){ //versiones recientes
            ContentResolver resolver = getContentResolver(); //para manejar los values
            ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = "foto2" + "@" + tiempo;

            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
            Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


            try {
                outputStream = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imageUri, values, null, null);
        }
        else{ //Apis mas antiguas < 28
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = "foto2" + "@" + tiempo + ".jpg"; //nombre del archivo

            file = new File(imageDir, filename);

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        boolean saved = listaFotos.get(0).compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //para la calidad y compresión del archivo
        if (saved){
            Toast.makeText(this, "Imagen registrada: " + "nombreFoto", Toast.LENGTH_SHORT).show();
        }

        if (outputStream != null){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (file != null){
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
        }

    }
    /*
     * implementacion permisos de almacenamiento y guardado de fotos
     */



    /*
     * implementacion preguntar por permisos de camara y almacenamiento
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        else if(requestCode == REQUEST_PERMISSION_WRITE_STORAGE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                guardarFoto1();
                guardarFoto2();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    /*
     * implementacion preguntar por permisos de camara y almacenamiento
     */

    /*
     * implementacion de Camara
     */



    /*
     * implementacion de NavigationDrawer
     */

    /**
     * método para aplicar toogle
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);
    }

    /**
     * Método para sincronizar toogle
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }

    /**
     * método para activar icono sanwich en NavigationDrawer
     * @return
     */
    private ActionBarDrawerToggle setDrawerToogle() {
        return new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * método para detectar click en el icono sandwich
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * método para detectar items del drawer
     * @param item
     * @return
     */
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

    /*
     *
     *
     * implementacion de NavigationDrawer
     *
     *
     *
     */


}