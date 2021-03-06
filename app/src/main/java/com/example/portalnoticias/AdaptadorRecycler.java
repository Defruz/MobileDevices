package com.example.portalnoticias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {

    Context context;
    ArrayList<Articulo> listaArticulo;
    Articulo articulo;
    Bitmap bitmap;
    Bitmap bitmap2;
    String idAux;
    static Toolbar toolbar_botones;
    static FloatingActionButton boton_editar, boton_eliminar;

    //Esta clase se utiliza para crear la vista, en estilo tarjeta, de las noticias sin detalle
    //Utilizamos tanto ScrollableView como CardView para lograr esto
    public AdaptadorRecycler(Context context, ArrayList<Articulo> listaArticulo) {
        this.context = context;
        this.listaArticulo = listaArticulo;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noticia_home, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Nos ayudamos de este metodo para que al clicar en una noticia nos lleve a la propia noticia
        //en detalle, para ello utilizamos otra clase y otro layout
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VistaArticulo.class);

                String link = "https://sanger.dia.fi.upm.es/pmd-task/article/" + listaArticulo.get(position).getId();
                URL url = null;
                try {
                    url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                articulo = Rest.consultaArticulo(url);
                bitmap2 = null;


                bitmap = Utility.base64StringToImg(articulo.getImage_data());
                if (bitmap != null) {
                    bitmap2 = Bitmap.createScaledBitmap(bitmap, 175, 150, true);
                }


                intent.putExtra("titulo", articulo.getTitle());
                intent.putExtra("subtitulo", articulo.getSubtitle());
                intent.putExtra("categoria", articulo.getCategory());
                intent.putExtra("cuerpo", articulo.getBody());

                intent.putExtra("foto", bitmap2);

                intent.putExtra("resumen", articulo.getAbstract());
                intent.putExtra("user", articulo.getUsername());
                intent.putExtra("fecha", articulo.getUpdate_date());

                context.startActivity(intent);
            }
        });

        holder.titulo.setText(listaArticulo.get(position).getTitle());
        holder.abstracto.setText(listaArticulo.get(position).getAbstract());
        holder.categoria.setText(listaArticulo.get(position).getCategory());
        Bitmap bitmap = Utility.base64StringToImg(listaArticulo.get(position).getThumbnail_image());
        if (bitmap != null) {
            holder.miniatura.setImageBitmap(bitmap);
        }
        holder.id.setText(listaArticulo.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if (listaArticulo != null) {
            return listaArticulo.size();
        }
        return 0;
    }

    //A partir de esta Inner Class se crea cada tarjeta y podemos trabajar con metodos internos
    //para cada una de ellas
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, abstracto, categoria, id;
        ImageView miniatura;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            abstracto = itemView.findViewById(R.id.abstracto);
            categoria = itemView.findViewById(R.id.categoria);
            miniatura = itemView.findViewById(R.id.imagen_miniatura);
            cardView = itemView.findViewById(R.id.cardview);
            boton_editar = itemView.findViewById(R.id.boton_editar);
            boton_eliminar = itemView.findViewById(R.id.boton_eliminar);
            toolbar_botones = itemView.findViewById(R.id.toolbar_botones);
            id = itemView.findViewById(R.id.idSecreto);

            //De este modo cada tarjeta se actualiza de una forma diferente si el usuario
            //esta logueado o no
            if(Rest.getCabecera() != "noLog"){
                cambiarEdicion();
            }
            else{
                eliminarEdicion();
            }

            //Se define el funcionamiento de cada boton de la tarjeta al pulsarlo para
            //poder utilizar las funciones de editar y eliminar articulos
            boton_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String link = "https://sanger.dia.fi.upm.es/pmd-task/article/" + id.getText().toString();
                    URL url = null;
                    try {
                        url = new URL(link);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    articulo = Rest.consultaArticulo(url);

                    Intent intent = new Intent(context, EditarArticulo.class);
                    bitmap2 = null;
                    if (articulo.getImage_data() != null){
                        bitmap = Utility.base64StringToImg(articulo.getImage_data());
                    }
                    if (bitmap != null) {
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 175, 150, true);
                    }
                    intent.putExtra("id", articulo.getId());
                    intent.putExtra("titulo", articulo.getTitle());
                    intent.putExtra("subtitulo", articulo.getSubtitle());
                    intent.putExtra("categoria", articulo.getCategory());
                    intent.putExtra("cuerpo", articulo.getBody());
                    intent.putExtra("foto", bitmap2);
                    intent.putExtra("resumen", articulo.getAbstract());

                    context.startActivity(intent);
                }
            });

            boton_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EliminarArticulo.class);
                    intent.putExtra("id", id.getText().toString());
                    context.startActivity(intent);
                }
                });
        }
    }

    //Meotodos auxiliares relacionados con la visibilidad de botones necesarios al estar logueado
    public static void cambiarEdicion() {
        toolbar_botones.setVisibility(View.VISIBLE);
    }

    public static void eliminarEdicion() {
        toolbar_botones.setVisibility(View.GONE);
    }
}
