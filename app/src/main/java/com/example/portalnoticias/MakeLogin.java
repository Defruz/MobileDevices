package com.example.portalnoticias;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class MakeLogin extends AsyncTask<Object, Void, String> {
    @Override
    protected String doInBackground(Object... objs) {
        try {
            // realizar conexion
            URL url = (URL) objs[0];
            HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);

            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.connect();

            JsonObject userCredentials = new JsonObject();
            userCredentials.addProperty("username", (String)objs[1]);
            userCredentials.addProperty("passwd", (String)objs[2]);

            // enviar informacion del usuario
            OutputStream os = myURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(userCredentials.toString());
            osw.flush();
            osw.close();

            // leer lo devuelto por el servidor
            InputStream is = myURLConnection.getInputStream();

            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();

            // convertir a formato json el string obtenido como respuesta del servidor
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PostHeaders resEnJava = gson.fromJson(result, PostHeaders.class);

            return resEnJava.getAuthorization() + " apikey=" + resEnJava.getApikey();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
