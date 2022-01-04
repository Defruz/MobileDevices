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

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {

    Context context;
    ArrayList<Articulo> listaArticulo;

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
                intent.putExtra("id", listaArticulo.get(position).getId());
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
