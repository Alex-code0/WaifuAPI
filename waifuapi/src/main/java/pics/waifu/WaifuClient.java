package pics.waifu;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

public class WaifuClient {

    public void downloadImage(String rating, String type) {
        try {
            String imageUrl = getURL(rating, type);
            if (imageUrl.isEmpty()) {
                System.err.println("Failed to fetch the image URL.");
                return;
            }

            // Extract file name from the URL
            URI uri = URI.create(imageUrl);
            String fileName = Paths.get(uri.getPath()).getFileName().toString();

            // Create path for Downloads folder
            Path downloadPath = Paths.get(System.getProperty("user.home"), "Downloads", fileName);

            // Download and save the file
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream())) {
                Files.copy(inputStream, downloadPath);
                System.out.println("Image downloaded successfully to: " + downloadPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getURL(String rating, String type) {
        try {
            URI uri = new URI(String.format("%s/%s/%s", Constants.BaseURL, rating, type));
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream())) {
                String response = new String(inputStream.readAllBytes());
                JSONObject json = new JSONObject(response);
                return json.getString("url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}