package com.example.portalnoticias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

public class crearArticulo extends AppCompatActivity {
    EditText titulo, subtitulo, resumen, body;
    Spinner categoria;
    Button cargarImagen, subirArticulo;
    ImageView imagen;
    String categoria_seleccionada;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_articulo);

        titulo = findViewById(R.id.editText_creaTitulo);
        subtitulo = findViewById(R.id.editText_creaSubtitulo);
        resumen = findViewById(R.id.editText_creaResumen);
        body = findViewById(R.id.editText_creaBody);

        categoria = findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);

        cargarImagen = findViewById(R.id.button_cargarImagen);
        subirArticulo = findViewById(R.id.button_subirArticulo);

        imagen = findViewById(R.id.imageView_imagen);

        error = findViewById(R.id.textView_error);


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

                // articulo.setImagen


                if(articulo.getTitle() == null || articulo.getBody() == null || articulo.getAbstract() == null ||
                articulo.getSubtitle() == null || articulo.getCategory() == null){
                    error.setVisibility(View.VISIBLE);
                }
                else{
                    Rest.creActualiza(articulo, url);
                }
            }
        });

         class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                categoria_seleccionada = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

    }
}
