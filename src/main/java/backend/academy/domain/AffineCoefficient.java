package backend.academy.domain;

import java.util.Random;

/**
 * Запись для хранения аффинных коэффициентов.
 * Предоставляет метод для генерации случайных коэффициентов, которые удовлетворяют определённым условиям.
 */
public record AffineCoefficient(double a, double b, double d, double e, double c, double f) {

    /**
     * Генерирует случайные аффинные коэффициенты, удовлетворяющие заданным ограничениям.
     *
     * @param random Генератор случайных чисел.
     * @return Сгенерированный объект AffineCoefficient.
     */
    public static AffineCoefficient generate(Random random) {
        double a;
        double b;
        double d;
        double e;
        double c;
        double f;

        do {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
            c = random.nextDouble(-1, 1);
            f = random.nextDouble(-1, 1);
        } while (!isValid(a, b, d, e));

        return new AffineCoefficient(a, b, d, e, c, f);
    }

    private static  boolean isValid(double a, double b, double d, double e) {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1)
            && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
            && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow((a * e - b * d), 2));
    }
}
