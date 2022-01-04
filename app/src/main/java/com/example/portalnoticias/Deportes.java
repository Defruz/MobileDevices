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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deportes, null);

        recyclerViewDeportes = v.findViewById(R.id.recycler_deportes);
        int id = 1;
        listaBuena = new ArrayList<Articulo>();
        while(listaBuena.size() < 10){
            for(int i = 0; i<listaBuena.size();i++){
                System.out.println (listaBuena.get(i));
            }
            String link = "https://sanger.dia.fi.upm.es/pmd-task/articles/10/" + id;
            System.out.println(link);
            try {
                url = new URL (link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            listaArticulos = Rest.consultaLista(url);
            if (listaArticulos != null){
                for(int i = 0; i<listaArticulos.size();i++){
                    if(listaArticulos.get(i).getCategory() != null && listaArticulos.get(i).getCategory().equals("Deportes")){
                        listaBuena.add(listaArticulos.get(i));
                    }
                }
            }
            id += 10;
        }
        recyclerViewDeportes.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaBuena);
        recyclerViewDeportes.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return v;
    }
}
