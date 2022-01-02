package com.example.portalnoticias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem todas,nacional, economia, deportes, tecnologia;
    ManejadorFragmentos manejadorFragmentos;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
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


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() >= 0 && tab.getPosition() <= 5){
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

    }
}