package backend.academy.transformation;

import backend.academy.domain.AffineCoefficient;
import backend.academy.domain.Point;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для выполнения аффинных преобразований точек.
 * Применяет случайно сгенерированные коэффициенты аффинной матрицы.
 */
public class AffineTransformation implements Transformation {

    private final AffineCoefficient affineCoefficient;

    /**
     * Конструктор для создания аффинного преобразования со случайными коэффициентами.
     */
    public AffineTransformation() {
        this.affineCoefficient = AffineCoefficient.generate(ThreadLocalRandom.current());
    }

    /**
     * Применяет аффинное преобразование к точке.
     *
     * @param point точка для преобразования
     * @return преобразованная точка
     */
    @Override
    public Point transform(Point point) {
        double x = point.x() * affineCoefficient.a() + point.y() * affineCoefficient.b() + affineCoefficient.c();
        double y = point.x() * affineCoefficient.d() + point.y() * affineCoefficient.e() + affineCoefficient.f();
        return new Point(x, y);
    }
}
