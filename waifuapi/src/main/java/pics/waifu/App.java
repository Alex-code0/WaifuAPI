package pics.waifu;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        WaifuClient client = new WaifuClient();

        String rating = JOptionPane.showInputDialog("Enter the image rating (e.g., SFW/NSFW):");
        if (rating == null || rating.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rating cannot be empty. Exiting...");
            return;
        }

        String type = JOptionPane.showInputDialog("Enter the image type (e.g., waifu, neko, etc.):");
        if (type == null || type.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Type cannot be empty. Exiting...");
            return;
        }

        client.downloadImage(rating.toLowerCase(), type.toLowerCase());
    }
}