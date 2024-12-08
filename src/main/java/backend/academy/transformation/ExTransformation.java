package backend.academy.transformation;

import backend.academy.domain.Point;

public class ExTransformation implements Transformation {
    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double o = Math.atan(p.x() / p.y());
        double p0 = Math.sin(o + r);
        double p1 = Math.cos(o - r);
        double newX = r * (Math.pow(p0, 3) + Math.pow(p1, 3));
        double newY = r * (Math.pow(p0, 3) - Math.pow(p1, 3));
        return new Point(newX, newY);
    }
}
