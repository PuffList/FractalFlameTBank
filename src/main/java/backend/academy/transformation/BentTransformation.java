package backend.academy.transformation;

import backend.academy.domain.Point;

public class BentTransformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double newX = p.x() >= 0 ? p.x() : 2 * p.x();
        double newY = p.y() >= 0 ? p.y() : p.y() / 2;
        return new Point(newX, newY);
    }
}
