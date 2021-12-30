import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;

public class TestHTTPSimple {
	
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://postman-echo.com/get?param1=a&param2=2");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//String params = "hcslkcn";
			
			//conn.setRequestProperty("Content-Length", params.getBytes().length + "");
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			InputStream is = conn.getInputStream();
			
			String result = "";
			BufferedReader reader = new BufferedReader( 
					new InputStreamReader( is )  );
			String line = null;
			while( ( line = reader.readLine() ) != null )  {
				System.out.println(line);
				result += line;
			}
			reader.close();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			PostResult resEnJava = gson.fromJson(result,PostResult.class);
			
			System.out.println(resEnJava);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
