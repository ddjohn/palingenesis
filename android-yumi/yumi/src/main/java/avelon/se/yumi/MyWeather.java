package avelon.se.yumi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MyWeather implements Runnable {
    private static final String TAG = MyWeather.class.getSimpleName();

    public MyWeather() {

        Thread t = new Thread(this);
        t.start();
    }

    private String curl() throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather");

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setDoOutput(true);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("q", "gothenburg");
        parameters.put("APPID", "5f755621998dc62062fb1a813b88049b");
        Log.e(TAG, "query=" + ParameterStringBuilder.getParamsString(parameters));

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));


        //out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.writeBytes("q=gothenburg&APPID=5f755621998dc62062fb1a813b88049b");
        out.flush();
        out.close();

        int status = con.getResponseCode();
        Log.e(TAG, "response=" + status);

        String contentType = con.getHeaderField("Content-Type");
        Log.e(TAG, "contentType=" + contentType);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        con.disconnect();

        return content.toString();
    }

    @Override
    public void run() {
        while(true) {
            try {
                String content = curl();
                Log.e(TAG, "content=" + content);
            }
            catch(IOException e) {
                Log.e(TAG, "e=" + e);
            }
            try {
                Thread.sleep(60000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    {"coord":{"lon":-0.13,"lat":51.51},"weather":[{"id":300,"main":"Drizzle","description":"light intensity drizzle","icon":"09d"}],"base":"stations","main":{"temp":280.32,"pressure":1012,"humidity":81,"temp_min":279.15,"temp_max":281.15},"visibility":10000,"wind":{"speed":4.1,"deg":80},"clouds":{"all":90},"dt":1485789600,"sys":{"type":1,"id":5091,"message":0.0103,"country":"GB","sunrise":1485762037,"sunset":1485794875},"id":2643743,"name":"London","cod":200}
*/
}
