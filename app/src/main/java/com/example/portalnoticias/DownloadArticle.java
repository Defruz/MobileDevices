package com.example.portalnoticias;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

// Clase que extiende AsyncTask para poder realizar la conexion en otro thread, no sobrecargar la main activity
// y que se pueda hacer correctamente la conexion HTTP. Esta clase se encargara de descargar un articulo en concreto
// y de devolver el resultado como un objeto Articulo creado.
public class DownloadArticle extends AsyncTask<URL, Void, Articulo> {

    @Override
    protected Articulo doInBackground(URL... urls) {
        try {
            // realizar conexion
            HttpURLConnection myURLConnection = (HttpURLConnection) urls[0].openConnection();
            myURLConnection.setRequestMethod("GET");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(false);

            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.connect();

            // leer lo devuelto por el servidor en la peticion del get
            InputStream is = myURLConnection.getInputStream();

            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();

            // convertir a formato json el string obtenido como respuesta del servidor
            // crear array copia donde almacenar los articulos a partir del offset indicado
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Articulo article = gson.fromJson(result, Articulo.class);

            return article;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
