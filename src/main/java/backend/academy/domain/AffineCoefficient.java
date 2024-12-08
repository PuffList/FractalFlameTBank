package backend.academy.domain;

import java.util.Random;

public record AffineCoefficient(double a, double b, double d, double e, double c, double f) {
    public static AffineCoefficient generate(Random random) {
        double a, b, d, e, c, f;

        do {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
            c = random.nextDouble(-1, 1);
            f = random.nextDouble(-1, 1);
        } while (!IsValid(a, b, d, e));

        return new AffineCoefficient(a, b, d, e, c, f);
    }

    private static  boolean IsValid(double a, double b, double d, double e) {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1) &&
            (Math.pow(b, 2) + Math.pow(e, 2) < 1) &&
            (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow((a * e - b * d), 2));
    }
}
