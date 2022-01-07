package com.example.portalnoticias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

// Clase con la que se crea la nueva actividad tras pulsar el boton crear articulo desde la pantalla principal
public class CrearArticulo extends AppCompatActivity {
    EditText titulo, subtitulo, resumen, body;
    Spinner categoria;
    Button cargarImagen, subirArticulo;
    ImageView imagen;
    Boolean imagenAux;
    String categoria_seleccionada;
    TextView error;

    // Tendra todos los campos necesarios para la creacion de un nuevo articulo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_articulo);

        titulo = findViewById(R.id.editText_creaTitulo);
        subtitulo = findViewById(R.id.editText_creaSubtitulo);
        resumen = findViewById(R.id.editText_creaResumen);
        body = findViewById(R.id.editText_creaBody);

        categoria = findViewById(R.id.spinner_categoria);

        // Para la categoria se ha realizado con un Spinner que permite seleccionar una categoria entre unas previamente definidas,
        // de modo que no se introduzca con tildes o de alguna manera que no sea reconocido por la aplicacion despues.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);

        cargarImagen = findViewById(R.id.button_cargarImagen);
        subirArticulo = findViewById(R.id.button_subirArticulo);

        imagen = findViewById(R.id.imageView_imagen);
        imagenAux = false;

        error = findViewById(R.id.textView_error);

        cargarImagen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        // Si todos los campos son correctos se llamara al metodo de la clase Rest creada para crear el articulo.
        subirArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Articulo articulo = new Articulo();
                URL url = null;
                try {
                    url = new URL("https://sanger.dia.fi.upm.es/pmd-task/article");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                articulo.setTitle(titulo.getText().toString());
                articulo.setbody(body.getText().toString());
                articulo.setAbstract(resumen.getText().toString());
                articulo.setSubtitle(subtitulo.getText().toString());
                articulo.setCategory(categoria_seleccionada);

                // Se obtiene el bitmap de la imagen sacada de la galeria y posteriormente se transforma a Base64String
                // por medio de una funcion auxiliar ya proporcionada en la clase Utility.
                if (imagenAux){
                    Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
                    articulo.setImage_data(Utility.imgToBase64String(bitmap));
                }

                if(articulo.getTitle().equals("") ||articulo.getBody().equals("") || articulo.getAbstract().equals("")||
                articulo.getSubtitle().equals("")|| articulo.getCategory().equals("")){
                    error.setVisibility(View.VISIBLE);
                }
                else{
                    Rest.creActualiza(articulo, url);
                    finish();
                }
            }
        });

        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                categoria_seleccionada = parentView.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    // Metodo auxiliar que permite abrir la galeria para seleccionar una imagen de esta que se subira.
    private void cargarImagen() {
        imagenAux = true;
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/");
        startActivityForResult(gallery, 10);
    }

    // Como resultado de la seleccion en la galeria se generara una imagen que se almacenara en la
    // ImageView para despues procesarla como sea necesario.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri imageUri = data.getData();
            imagen.setImageURI(imageUri);
        }
    }
}
