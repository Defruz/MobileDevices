package com.example.portalnoticias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VistaArticulo extends AppCompatActivity {

    private TextView titulo;
    private TextView subtitulo;
    private TextView cuerpo;
    private TextView resumen;
    private TextView usuario;
    private TextView fecha;
    private TextView categoria;
    private ImageView foto;
    // clase en la que se asigna y se muestra  el contenido correspondiente a cada atributo del articulo
    // para la vista mas detallada de este.
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vista_articulo);
            Intent i = getIntent();

            titulo = findViewById(R.id.titulo_articulo);
            titulo.setText(i.getStringExtra("titulo"));

            subtitulo = findViewById(R.id.subtitulo_articulo);
            subtitulo.setText(i.getStringExtra("subtitulo"));

            cuerpo = findViewById(R.id.body_articulo);
            cuerpo.setText(i.getStringExtra("cuerpo"));

            resumen = findViewById(R.id.resumen_articulo);
            resumen.setText(i.getStringExtra("resumen"));

            usuario = findViewById(R.id.usuario_articulo);
            usuario.setText(i.getStringExtra("user"));

            fecha = findViewById(R.id.fecha_articulo);
            fecha.setText(i.getStringExtra("fecha"));

            categoria = findViewById(R.id.categoria_articulo);
            categoria.setText(i.getStringExtra("categoria"));

            foto = findViewById(R.id.imagen);
            Bitmap bitmap = i.getParcelableExtra("foto");
            if(bitmap == null){
                findViewById(R.id.imagen).setVisibility(View.GONE);
            }
            foto.setImageBitmap(bitmap);
    }
}