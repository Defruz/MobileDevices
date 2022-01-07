package com.example.portalnoticias;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Clase que extiende AsyncTask para poder realizar la conexion en otro thread, no sobrecargar la main activity
// y que se pueda hacer correctamente la conexion HTTP. Esta clase se encargara de eliminar un articulo en concreto.
public class MakeEliminar extends AsyncTask<Object, Void, Void> {
    @Override
    protected Void doInBackground(Object... objs) {
        if (Rest.getCabecera() != null) {
            try {
                URL url = (URL) objs[0];

                HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
                myURLConnection.setRequestMethod("DELETE");
                myURLConnection.setUseCaches(false);
                myURLConnection.setDoInput(true);
                myURLConnection.setDoOutput(true);

                myURLConnection.setRequestProperty("Authorization", Rest.getCabecera());
                myURLConnection.connect();

                myURLConnection.getResponseCode();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}