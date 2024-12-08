package backend.academy.transformation;

import backend.academy.domain.Point;

public class SpiralTransformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double o = Math.atan(p.x() / p.y());
        double newX = (Math.cos(o) + Math.sin(r)) / r;
        double newY = (Math.sin(o) - Math.cos(r)) / r;
        return new Point(newX, newY);
    }
}
