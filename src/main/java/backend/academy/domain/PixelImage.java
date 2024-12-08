package backend.academy.domain;

import java.util.stream.IntStream;

public class PixelImage {

    private final Pixel[] pixels;
    private final int width;
    private final int height;

    public PixelImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[width * height];
        IntStream.range(0, width * height).forEach(i -> this.pixels[i] = new Pixel(0, 0,0));
    }

    public Pixel getPixel(int x, int y) {
        return this.pixels[y * width + x];
    }
}
