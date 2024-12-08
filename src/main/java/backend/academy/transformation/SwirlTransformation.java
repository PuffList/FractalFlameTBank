package backend.academy.transformation;

import backend.academy.domain.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point transform(Point p) {
        double r2 = p.x() * p.x() + p.y() * p.y();
        return new Point(
            p.x() * Math.sin(r2) - p.y() * Math.cos(r2),
            p.x() * Math.cos(r2) + p.y() * Math.sin(r2)
        );
    }
}
