package backend.academy.transformation;

import backend.academy.domain.AffineCoefficient;
import backend.academy.domain.Point;

import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation implements Transformation {

    private final AffineCoefficient affineCoefficient;

    public AffineTransformation() {
        this.affineCoefficient = AffineCoefficient.generate(ThreadLocalRandom.current());
    }

    @Override
    public Point transform(Point point) {
        double x = point.x() * affineCoefficient.a() + point.y() * affineCoefficient.b() + affineCoefficient.c();
        double y = point.x() * affineCoefficient.d() + point.y() * affineCoefficient.e() + affineCoefficient.f();
        return new Point(x, y);
    }
}
