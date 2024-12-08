package backend.academy.render;

import backend.academy.domain.Color;
import backend.academy.domain.Pixel;
import backend.academy.domain.PixelImage;
import backend.academy.domain.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Класс для рендеринга изображения, представляющего пламя.
 * Отвечает за отрисовку точек, коррекцию гаммы и сохранение изображения в файл.
 */
public class Renderer {

    private static final PrintStream ERR = System.err;
    private static final double GAMMA_CF = 1.5;
    private static final double DEGREE = 360.0;
    private final BufferedImage image;
    private final PixelImage pixelImage;
    private final int axesCount;

    /**
     * Конструктор для инициализации рендерера.
     * Создает изображение заданного размера и инициализирует необходимые параметры.
     *
     * @param width ширина изображения
     * @param height высота изображения
     * @param axesCount количество осей симметрии
     * @param outputFile файл для сохранения изображения
     */
    public Renderer(int width, int height, int axesCount, String outputFile) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.pixelImage = new PixelImage(width, height);
        this.axesCount = axesCount;
    }

    /**
     * Отрисовывает точку на изображении с учетом симметрии и заданного цвета.
     *
     * @param point точка, которую нужно отрисовать
     * @param color цвет точки
     */
    public void renderPoint(Point point, Color color) {
        List<Point> points = getSymmetryPoints(point);
        points.add(point);
        points.forEach(p -> setPixel(p.x(), p.y(), color));
    }

    /**
     * Применяет коррекцию гаммы к изображению.
     */
    public void gammaCorrection() {
        double maxNormal = 0.0;
        double gamma = GAMMA_CF;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = pixelImage.getPixel(x, y);
                if (pixel.pointsCount() != 0) {
                    pixel.normal(Math.log10(pixel.pointsCount()));
                    if (pixel.normal() > maxNormal) {
                        maxNormal = pixel.normal();
                    }
                }
            }
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = pixelImage.getPixel(x, y);
                pixel.normal(pixel.normal() / maxNormal);
                int red = (int) (pixel.red() * Math.pow(pixel.normal(), (1.0 / gamma)));
                int green = (int) (pixel.green() * Math.pow(pixel.normal(), (1.0 / gamma)));
                int blue = (int) (pixel.blue() * Math.pow(pixel.normal(), (1.0 / gamma)));
                pixel.setRGB(red, green, blue);
            }
        }
    }

    /**
     * Отображает изображение, перенося каждый пиксель в объект BufferedImage.
     */
    public void renderImage() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = pixelImage.getPixel(x, y);
                image.setRGB(x, y, pixel.getColor().getRGB());
            }
        }
    }

    /**
     * Сохраняет изображение в файл.
     *
     * @param outputFile путь к файлу, в который будет сохранено изображение
     */
    public void saveImage(String outputFile) {
        try {
            ImageIO.write(image, "png", new File(outputFile));
        } catch (Exception e) {
            ERR.println("Ошибка сохранения изображения: " + e.getMessage());
        }
    }

    private void setPixel(double x, double y, Color color) {
        int xInt = (int) ((x + 1) * image.getWidth() / 2);
        int yInt = (int) ((y + 1) * image.getHeight() / 2);

        if (xInt >= 0 && xInt < image.getWidth() && yInt >= 0 && yInt < image.getHeight()) {
            Pixel pixel = pixelImage.getPixel(xInt, yInt);
            synchronized (pixel) {
                pixel.addPoint(color);
            }
        }
    }

    private List<Point> getSymmetryPoints(Point point) {
        List<Point> points = new ArrayList<>();
        double angleStep = DEGREE / axesCount;

        for (int i = 0; i < axesCount; i++) {
            double angle = Math.toRadians(i * angleStep);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double newX = point.x() * cos + point.y() * sin;
            double newY = -point.x() * sin + point.y() * cos;
            points.add(new Point(newX, newY));
        }

        return points;
    }
}
