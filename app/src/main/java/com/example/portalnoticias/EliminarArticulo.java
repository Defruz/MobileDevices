package com.example.portalnoticias;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

// Clase con la que se crea la nueva actividad tras pulsar el boton eliminar articulo desde la pantalla principal
// cuando se esta logeado.
public class EliminarArticulo extends AppCompatActivity {
    Button eliminarArticulo, salvarArticulo;
    String id;

    // Tendra dos botones: uno para confirmar la eliminacion del articulo y otro que cancelara la accion.
    // De este modo nos aseguramos de que no se elimine un articulo por error.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_articulo);

        eliminarArticulo = findViewById(R.id.button_siBorrar);
        salvarArticulo = findViewById(R.id.button_noBorrar);

        Intent i = getIntent();
        id = i.getStringExtra("id").toString();

        eliminarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://sanger.dia.fi.upm.es/pmd-task/article/" + id;
                System.out.println(link);
                URL url = null;
                try {
                    url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Rest.delete(url);
                finish();
            }
        });

        salvarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

