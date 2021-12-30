import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Post
 */
public class Post {

    public static String login(URL myURL, JsonObject userCredentials) {

        String resultado = "";
        try {
            // realizar conexion
            HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);

            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.connect();
             
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
            resultado = gson.toJson(resEnJava);

            return resultado;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static String consulta(URL urlConsulta) {
        String resultado = "";
        try {
            String[] url = urlConsulta.toString().split("/");
            int limit = Integer.valueOf(url[url.length - 2]);
            int offset = Integer.valueOf(url[url.length - 1]);

            // realizar conexion
            HttpURLConnection myURLConnection = (HttpURLConnection) urlConsulta.openConnection();
            myURLConnection.setRequestMethod("GET");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);

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
            ArticuloHeaders[] article = gson.fromJson(result, ArticuloHeaders[].class);
            ArticuloHeaders[] copia = new ArticuloHeaders[limit - offset + 1];

            int i = 0;
            while (offset <= article.length) {
                copia[i] = article[offset - 1];
                offset++;
                i++;
            }
            resultado = gson.toJson(copia);
            return resultado;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static void main(String[] args) {
        URL myURLlogin, urlConsulta;
        try {
            myURLlogin = new URL("https://sanger.dia.fi.upm.es/pmd-task/login");
            urlConsulta = new URL("https://sanger.dia.fi.upm.es/pmd-task/articles/3/2");
            JsonObject userCredentials = new JsonObject();
            userCredentials.addProperty("username", "GR_16");
            userCredentials.addProperty("passwd", "332002");
            // System.out.println(login(myURLlogin, userCredentials));
            System.out.println(consulta(urlConsulta));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}