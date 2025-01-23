package pics.waifu;

import pics.waifu.Constants;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WaifuClient {
    
    public void downloadImage(String rating, String type) {

    }

    private String getURL(String rating, String type) {
        try {
            URL url = new URL(String.format("%s/%s/%s", Constants.BaseURL, rating, type));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            JSONObject json = new JSONObject(reader.readLine());

            return json.getString("url");
        } catch (Exception ignored) {}

        return "";
    }
}