package backend.academy.transformation;

import backend.academy.domain.Point;

public class PowerTransformation implements Transformation {

    @Override
    public Point transform(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double theta = Math.atan2(p.y(), p.x());
        double pow = Math.pow(r, Math.sin(theta));
        double newX = pow * Math.cos(theta);
        double newY = pow * Math.sin(theta);
        return new Point(newX, newY);
    }
}
