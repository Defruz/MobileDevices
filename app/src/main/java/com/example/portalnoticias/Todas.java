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
import java.util.concurrent.ExecutionException;

public class Todas extends Fragment {

    ArrayList<Articulo> listaArticulos;
    AdaptadorRecycler adapter;
    private RecyclerView recyclerViewTodos;
    URL url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        try {
            url = new URL ("https://sanger.dia.fi.upm.es/pmd-task/articles/10/1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        View v = inflater.inflate(R.layout.todas, null);

        recyclerViewTodos = v.findViewById(R.id.recycler_todas);
        listaArticulos = Rest.consultaLista(url);
        recyclerViewTodos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorRecycler(getContext(),listaArticulos);
        recyclerViewTodos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
        }
}
