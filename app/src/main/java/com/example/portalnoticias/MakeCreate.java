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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MakeCreate extends AsyncTask<Object, Void, Articulo> {
    @Override
    protected Articulo doInBackground(Object... objs) {
        if (Rest.getCabecera() != null) {
            try {
                URL url = (URL) objs[1];
                HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
                myURLConnection.setRequestMethod("POST");
                myURLConnection.setUseCaches(false);
                myURLConnection.setDoInput(true);
                myURLConnection.setDoOutput(true);

                myURLConnection.setRequestProperty("Content-Type", "application/json");
                myURLConnection.setRequestProperty("Authorization", Rest.getCabecera());
                myURLConnection.connect();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String articuloJson = gson.toJson(objs[0]);

                // enviar informacion del usuario
                OutputStream os = myURLConnection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                //String prueba = "{\"is_public\": \"a \", \"abstract\": \"The successor of Steve Jobs also met with leaders of Spain?s tech industry and praised the country?s ?creativity?\",\"body\":\"Apple CEO Tim Cook paid a surprise visit to Spain on Thursday, when he met with Prime Minister Pedro Sánchez and with Spanish app and game developers. Cook was also there to promote the Spanish launch of the company?s smart speaker HomePod, in a presentation that featured the singer Rosalía.\u003cbr\u003e\u003cbr\u003eAccording to a statement by the Prime Minister?s Office, Sánchez and Cook exchanged remarks about future challenges ?in the current context of economic globalization and accelerated technological change.? Sánchez also discussed his plans to make Spain a ?Startup Nation? by ?creating a business climate that encourages entrepreneurship and innovation in the fields of new technologies and digitalization.?\u003cbr\u003e\u003cbr\u003eThe visit completed Cook?s European tour, which also took him to France to see President Emmanuel Macron and to Germany to talk with Chancellor Angela Merkel.\u003cbr\u003e\u003cbr\u003eBefore the face-to-face with Sánchez, which was not on the PM?s official agenda, Cook met with Spanish developers from Lingo Kids, a company that has created a popular app to help children learn English, and from Social Point, which makes video games for cellphones  and posts annual sales of $120 million.\u003cbr\u003e\u003cbr\u003eBut one of the main goals of the visit was to introduce the new HomePod, which will be available in Spanish stores starting today. With the product, Apple is seeking to compete with Amazon?s smart speaker Echo and Google?s Home assistant. The product became available with Spanish-language support a few weeks ago.\",\"subtitle\": \"Spain is a growing market that stands out for its creativity over other countries\",\"category\": \"Technology\",\"title\": \"Apple CEO Tim Cook meets with PM in surprise visit\",\"image_description\": \" a\", \"image_media_type\": \"image/jpeg\"}";

                osw.write(articuloJson);
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
                Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
                Articulo articuloSalida = gson2.fromJson(result, Articulo.class);

                return articuloSalida;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}

