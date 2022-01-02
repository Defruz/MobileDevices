package com.example.portalnoticias;

import java.util.ArrayList;

public class ListaNoticias {
    private String status;
    private int totalResults;
    private ArrayList<Articulo> lista;


    public ListaNoticias(String status, int totalResults, ArrayList<Articulo> lista) {
        this.status = status;
        this.totalResults = totalResults;
        this.lista = lista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Articulo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articulo> lista) {
        this.lista = lista;
    }
}
