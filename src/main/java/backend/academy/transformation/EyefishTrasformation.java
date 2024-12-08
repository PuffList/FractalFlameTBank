package backend.academy.transformation;

import backend.academy.domain.Point;

public class EyefishTrasformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double newX = (2 * p.x()) / (r + 1);
        double newY = (2 * p.y()) / (r + 1);
        return new Point(newX, newY);
    }
}
