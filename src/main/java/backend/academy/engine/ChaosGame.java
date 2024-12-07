package backend.academy.engine;

import backend.academy.domain.Point;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class ChaosGame {

    private final List<Transformation> transformations;
    private final Random random;

    public ChaosGame(List<Transformation> transformations) {
        this.transformations = transformations;
        this.random = new Random();
    }

    /**
     * Выполняет генерацию точек.
     *
     * @param iterations    количество итераций
     * @param initialPoint  начальная точка
     * @param callback      обратный вызов для обработки каждой точки
     */
    public void generate(int iterations, Point initialPoint, ChaosGameCallback callback) {
        Point currentPoint = initialPoint;

        for (int i = 0; i < iterations; i++) {
            // Выбираем случайную трансформацию
            Transformation transformation = transformations.get(random.nextInt(transformations.size()));
            // Применяем трансформацию
            System.out.printf("Before: (%.3f, %.3f)", currentPoint.x(), currentPoint.y());
            currentPoint = transformation.transform(currentPoint);
            System.out.printf(" -> After: (%.3f, %.3f)%n", currentPoint.x(), currentPoint.y());
            // Передаём точку в callback
            callback.onPointGenerated(currentPoint);
        }
    }

    /**
     * Интерфейс для передачи результатов точек.
     */
    @FunctionalInterface
    public interface ChaosGameCallback {
        void onPointGenerated(Point point);
    }
}
