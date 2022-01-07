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

public class Tecnologia extends Fragment {

    ArrayList<Articulo> listaArticulos;
    ArrayList<Articulo> listaBuena;
    AdaptadorRecycler adapter;
    private RecyclerView recyclerViewTecnologia;
    URL url;
    // Con esta clase conseguimos mostrar en el recyclerView unicamente las noticias que tengan como categoria "Tecnología"
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try {
            url = new URL ("https://sanger.dia.fi.upm.es/pmd-task/articles/100/1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        View v = inflater.inflate(R.layout.tecnologia, null);

        recyclerViewTecnologia = v.findViewById(R.id.recycler_tecnologia);
        listaArticulos = Rest.consultaLista(url);
        listaBuena = new ArrayList<Articulo>();
        if (listaArticulos != null){
            for(int i = 0; i<listaArticulos.size() && listaBuena.size()<10;i++){
                if(listaArticulos.get(i).getCategory() != null && listaArticulos.get(i).getCategory().equals("Tecnología")){
                    listaBuena.add(listaArticulos.get(i));
                }
            }
        }
        recyclerViewTecnologia.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaBuena);
        recyclerViewTecnologia.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return v;
    }
}
