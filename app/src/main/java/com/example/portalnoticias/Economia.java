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

public class Economia extends Fragment {

    ArrayList<Articulo> listaArticulos;
    AdaptadorRecycler adapter;
    private RecyclerView recyclerViewEconomia;
    URL url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            url = new URL ("https://sanger.dia.fi.upm.es/pmd-task/articles/10/1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        View v = inflater.inflate(R.layout.economia, null);

        recyclerViewEconomia = v.findViewById(R.id.recycler_economia);
        listaArticulos = Rest.consultaLista(url);
        if (listaArticulos != null){
            for(int i = 0; i<listaArticulos.size();i++){
                if(!listaArticulos.get(i).getCategory().equals("Economía")){
                    listaArticulos.remove(i--);
                }
            }
        }
        recyclerViewEconomia.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaArticulos);
        recyclerViewEconomia.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return v;
    }
}
