package com.example.portalnoticias;

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
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Rest {
    private static String cabecera = "noLog";

    public static String getCabecera (){
        return cabecera;
    }

    public static void setCabecera (String aux){
       cabecera = aux;
    }

    public static void login(URL myURL, String username, String passwd) {
        try{
            String aux = new MakeLogin().execute(myURL, username, passwd).get();
            setCabecera(aux);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Articulo> consultaLista(URL urlLista) {
        try{
            return new DownloadList().execute(urlLista).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Articulo consultaArticulo(URL urlArticulo) {
        try{
            return new DownloadArticle().execute(urlArticulo).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Articulo creActualiza(Articulo articulo, URL urlCre) {
        try{
            return new MakeCreate().execute(articulo, urlCre).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(URL url){
        new MakeEliminar().execute(url);
    }

}
