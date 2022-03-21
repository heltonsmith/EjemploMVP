package com.heltonbustos.ejemplomvp01.interactor.modeloRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heltonbustos.ejemplomvp01.R;
import com.heltonbustos.ejemplomvp01.view.actividades.OtraActividad;
import com.heltonbustos.ejemplomvp01.view.actividades.VerRegistroUnico;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDator> {

    ArrayList<RegistroEquiposDatos> listDatos;
    Context context;

    public AdaptadorDatos(ArrayList<RegistroEquiposDatos> listDatos, Context context) {
        this.listDatos = listDatos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorDatos.ViewHolderDator onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registros_equipos, null, false);
        return new ViewHolderDator(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolderDator holder, int position) {
        Bitmap bit1 = listDatos.get(position).getB1();
        Bitmap bit2 = listDatos.get(position).getB2();
        String cod = listDatos.get(position).getCodigo();
        String nom = listDatos.get(position).getNombre();
        String fec = listDatos.get(position).getFecha();
        String bol = listDatos.get(position).getBolso();
        String car = listDatos.get(position).getCargador();

        holder.imagen1.setImageBitmap(bit1);
        holder.imagen2.setImageBitmap(bit2);
        holder.codigo.setText(cod);
        holder.nombre.setText(nom);
        holder.fecha.setText(fec);
        holder.bolso.setText(bol);
        holder.cargador.setText(car);
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


    public class ViewHolderDator extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagen1;
        ImageView imagen2;
        TextView codigo;
        TextView nombre;
        TextView fecha;
        TextView bolso;
        TextView cargador;

        public ViewHolderDator(@NonNull View itemView) {
            super(itemView);
            imagen1 = itemView.findViewById(R.id.img1Equipo);
            imagen2 = itemView.findViewById(R.id.img2Equipo);
            codigo = itemView.findViewById(R.id.txtCodigoEquipoL);
            nombre = itemView.findViewById(R.id.txtNombreEquipoL);
            fecha = itemView.findViewById(R.id.txtFechaEquipoL);
            bolso = itemView.findViewById(R.id.txtBolsoEquipoL);
            cargador = itemView.findViewById(R.id.txtCargadorEquipoL);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(
                    view.getContext(),
                    "Código: " + listDatos.get(getLayoutPosition()).getNombre(),
                    Toast.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(context, VerRegistroUnico.class);

            intent.putExtra("cod", listDatos.get(getLayoutPosition()).getCodigo());

            context.startActivity(intent);
        }
    }


}
