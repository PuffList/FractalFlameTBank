package backend.academy.transformation;

import backend.academy.domain.Point;

public class SphericalTransformation implements Transformation{

    @Override
    public Point transform(Point p) {
        double r2 = p.x() * p.x() + p.y() * p.y();
        System.out.println(p.x() / r2 + " " + p.y() / r2);
        return new Point(p.x() / r2, p.y() / r2);
    }
}
