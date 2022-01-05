package com.example.portalnoticias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem todas,nacional, economia, deportes, tecnologia;
    ManejadorFragmentos manejadorFragmentos;
    Toolbar toolbar;
    static FloatingActionButton login, close, categorias, boton_agregar;
    Button cerrar, continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(null);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_botones);
        setSupportActionBar(toolbar);

        todas = findViewById(R.id.categoria_todas);
        nacional = findViewById(R.id.categoria_nacional);
        economia = findViewById(R.id.categoria_economia);
        deportes = findViewById(R.id.categoria_deportes);
        tecnologia = findViewById(R.id.categoria_tecnologia);

        ViewPager viewPager = findViewById(R.id.visualizado);
        tabLayout = findViewById(R.id.categorias);

        manejadorFragmentos = new ManejadorFragmentos(getSupportFragmentManager(), 5);
        viewPager.setAdapter(manejadorFragmentos);

        login = findViewById(R.id.login);
        close = findViewById(R.id.close);
        cerrar = findViewById(R.id.buttonSi);
        continuar = findViewById(R.id.buttonNo);
        categorias = findViewById(R.id.boton_categorias);
        boton_agregar = findViewById(R.id.boton_agregar);

        final SharedPreferences recordatorio = getSharedPreferences("recuerdame", Context.MODE_PRIVATE);

        String cabecera = recordatorio.getString("apikey", "");

        if(cabecera != null && !cabecera.equals("noLog")){
            Rest.setCabecera(cabecera);
            login.setVisibility(View.GONE);
            close.setVisibility(View.VISIBLE);
            boton_agregar.setVisibility(View.VISIBLE);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() >= 0 || tab.getPosition() < 5){
                    manejadorFragmentos.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayout.setVisibility(View.VISIBLE);
                categorias.setVisibility(View.GONE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginForm.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar.setVisibility(View.VISIBLE);
                continuar.setVisibility(View.VISIBLE);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar.setVisibility(View.GONE);
                continuar.setVisibility(View.GONE);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar.setVisibility(View.GONE);
                continuar.setVisibility(View.GONE);
                Rest.setCabecera("noLog");
                close.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor = recordatorio.edit();
                editor.putString("apikey", Rest.getCabecera());
                editor.apply();
                AdaptadorRecycler.eliminarEdicion();
                boton_agregar.setVisibility(View.GONE);
            }
        });
    }
    public static void cambiarLogin (){
        login.setVisibility(View.GONE);
        close.setVisibility(View.VISIBLE);
        boton_agregar.setVisibility(View.VISIBLE);

    }
}