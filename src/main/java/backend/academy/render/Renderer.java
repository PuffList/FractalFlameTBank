package backend.academy.render;

import backend.academy.domain.Color;
import backend.academy.domain.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Renderer {

    private final BufferedImage image;

    public Renderer(int width, int height, String outputFile) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void render(Point p, Color color) {
        double scale = 1.0; // Масштабирование для фрактала
        double translateX = 0; // Возможное смещение
        double translateY = 0;

        int x = (int) (((p.x() * scale) + translateX + 1) * image.getWidth() / 2);
        int y = (int) (((p.y() * scale) + translateY + 1) * image.getHeight() / 2);

        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, color.getRGB());
            /*int rgb = image.getRGB(x, y);
            Color existingColor = new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);

            // Изменение плотности
            int newR = Math.min(255, (existingColor.r() + color.r()) / 2);
            int newG = Math.min(255, (existingColor.g() + color.g()) / 2);
            int newB = Math.min(255, (existingColor.b() + color.b()) / 2);

            image.setRGB(x, y, new Color(newR, newG, newB).getRGB());*/
        }
    }

    public void applyGammaCorrection(double gamma) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int r = (int) (255 * Math.pow(((rgb >> 16) & 0xFF) / 255.0, gamma));
                int g = (int) (255 * Math.pow(((rgb >> 8) & 0xFF) / 255.0, gamma));
                int b = (int) (255 * Math.pow((rgb & 0xFF) / 255.0, gamma));
                image.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
    }

    public void saveImage(String outputFile) {
        try {
            ImageIO.write(image, "png", new File(outputFile));
        } catch (Exception e) {
            System.err.println("Ошибка сохранения изображения: " + e.getMessage());
        }
    }
}
