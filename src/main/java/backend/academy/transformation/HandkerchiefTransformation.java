package backend.academy.transformation;

import backend.academy.domain.Point;

public class HandkerchiefTransformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double theta = Math.atan2(p.y(), p.x());
        double newX = r * Math.sin(theta + r);
        double newY = r * Math.cos(theta - r);
        return new Point(newX, newY);
    }
}
