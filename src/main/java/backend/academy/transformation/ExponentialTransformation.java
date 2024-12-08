package backend.academy.transformation;

import backend.academy.domain.Point;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point transform(Point p) {
        double newX = Math.exp(p.x() - 1) * Math.cos(Math.PI * p.y());
        double newY = Math.exp(p.x() - 1) * Math.sin(Math.PI * p.y());
        return new Point(newX, newY);
    }
}
