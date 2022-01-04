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

public class Nacional extends Fragment {

    ArrayList<Articulo> listaArticulos;
    AdaptadorRecycler adapter;
    private RecyclerView recyclerViewNacional;
    URL url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            url = new URL ("https://sanger.dia.fi.upm.es/pmd-task/articles/10/1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        View v = inflater.inflate(R.layout.nacional, null);

        recyclerViewNacional = v.findViewById(R.id.recycler_nacional);
        listaArticulos = new ArrayList<>();
        recyclerViewNacional.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaArticulos);
        recyclerViewNacional.setAdapter(adapter);

        listaArticulos = Rest.consultaLista(url);

        if (listaArticulos!= null){
            for(int i = 0; i<listaArticulos.size();i++){
                if(listaArticulos.get(i).getCategory() != "Nacional"){
                    listaArticulos.remove(i--);
                }
            }
        }

        adapter.notifyDataSetChanged();
        return v;
    }
}
