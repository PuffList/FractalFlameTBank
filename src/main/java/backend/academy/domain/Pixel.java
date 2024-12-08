package backend.academy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int pointsCount;
    @Setter
    private double normal;

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.pointsCount = 0;
    }

    public void setRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void addPoint(Color color) {
        if (pointsCount == 0) {
            setRGB(color.r(), color.g(), color.b());
        } else {
            red = (red + color.r()) / 2;
            green = (green + color.g()) / 2;
            blue = (blue + color.b()) / 2;
        }
        this.pointsCount++;
    }

    public Color getColor() {
        return new Color(red, green, blue);
    }
}
