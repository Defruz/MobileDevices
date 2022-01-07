package com.example.portalnoticias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Deportes extends Fragment {

    ArrayList<Articulo> listaArticulos;
    ArrayList<Articulo> listaBuena;
    AdaptadorRecycler adapter;
    private RecyclerView recyclerViewDeportes;
    URL url;

    // Con esta clase conseguimos mostrar en el recyclerView unicamente las noticias que tengan como categoria "Deportes"
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deportes, null);

        try {
            url = new URL ("https://sanger.dia.fi.upm.es/pmd-task/articles/100/1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        listaArticulos = Rest.consultaLista(url);
        recyclerViewDeportes = v.findViewById(R.id.recycler_deportes);
        int id = 1;
        listaBuena = new ArrayList<Articulo>();
        if (listaArticulos != null){
            for(int i = 0; i<listaArticulos.size() && listaBuena.size()<10;i++){
                if(listaArticulos.get(i).getCategory() != null && listaArticulos.get(i).getCategory().equals("Deportes")){
                    listaBuena.add(listaArticulos.get(i));
                }
            }
        }
        recyclerViewDeportes.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaBuena);
        recyclerViewDeportes.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return v;
    }
}
