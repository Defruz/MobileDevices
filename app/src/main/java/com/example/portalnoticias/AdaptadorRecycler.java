package com.example.portalnoticias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {

    Context context;
    ArrayList<Articulo> listaArticulo;
    Articulo articulo;
    Bitmap bitmap;
    Bitmap bitmap2;

    public AdaptadorRecycler(Context context, ArrayList<Articulo> listaArticulo) {
        this.context = context;
        this.listaArticulo = listaArticulo;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noticia_home,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,vistaArticulo.class);

                String link = "https://sanger.dia.fi.upm.es/pmd-task/article/" + listaArticulo.get(position).getId();
                URL url = null;
                try {
                    url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                articulo = Rest.consultaArticulo(url);
                bitmap2= null;


                    bitmap = Utility.base64StringToImg(articulo.getImage_data());
                    if(bitmap != null){
                        bitmap2 = Bitmap.createScaledBitmap(bitmap,175,150,true);
                    }




                intent.putExtra("titulo", articulo.getTitle());
                intent.putExtra("subtitulo", articulo.getSubtitle() );
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
        if(bitmap != null){
            holder.miniatura.setImageBitmap(bitmap);
        }
    }


    @Override
    public int getItemCount() {
        if (listaArticulo != null){
            return listaArticulo.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, abstracto, categoria;
        ImageView miniatura;
        CardView cardView;

            public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            abstracto = itemView.findViewById(R.id.abstracto);
            categoria = itemView.findViewById(R.id.categoria);
            miniatura = itemView.findViewById(R.id.imagen_miniatura);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
