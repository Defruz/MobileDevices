import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Post
 */
public class Post {

    public static void login(JsonObject userCredentials) {
        String cuerpo = "Texto";
        URL myURL;
        try {
            // realizar conexion
            myURL = new URL("https://sanger.dia.fi.upm.es/pmd-task/login");
            HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);

            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.connect();

            OutputStream os = myURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(userCredentials.toString());
            osw.flush();
            osw.close();

            InputStream is = myURLConnection.getInputStream();

            String result = "";
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
            reader.close();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
			PostHeaders resEnJava = gson.fromJson(result,PostHeaders.class);
			
			System.out.println(resEnJava.getApikey());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JsonObject userCredentials = new JsonObject();
        userCredentials.addProperty("username", "GR_16");
        userCredentials.addProperty("passwd", "332002");
        login(userCredentials);

    }
}