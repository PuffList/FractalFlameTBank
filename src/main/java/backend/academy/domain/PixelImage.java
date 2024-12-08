package backend.academy.domain;

import java.util.stream.IntStream;

/**
 * Класс для представления изображения, состоящего из пикселей.
 * Каждый пиксель изображения имеет координаты и цвет.
 */
public class PixelImage {

    private final Pixel[] pixels;
    private final int width;
    private final int height;

    /**
     * Конструктор класса, инициализирует изображение заданной ширины и высоты.
     * Все пиксели инициализируются как черные (0, 0, 0).
     *
     * @param width ширина изображения
     * @param height высота изображения
     */
    public PixelImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[width * height];
        IntStream.range(0, width * height).forEach(i -> this.pixels[i] = new Pixel(0, 0, 0));
    }

    /**
     * Возвращает пиксель по координатам x и y.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     * @return пиксель изображения в указанной позиции
     */
    public Pixel getPixel(int x, int y) {
        return this.pixels[y * width + x];
    }
}
