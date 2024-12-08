package backend.academy.transformation;

import backend.academy.domain.Point;

public class PolarTransformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double o = Math.atan(p.x() / p.y());
        double newX = o / Math.PI;
        double newY = r - 1;
        return new Point(newX, newY);
    }
}
