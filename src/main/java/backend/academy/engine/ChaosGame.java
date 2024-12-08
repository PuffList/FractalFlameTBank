package backend.academy.engine;

import backend.academy.domain.Color;
import backend.academy.domain.Point;
import backend.academy.render.Renderer;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChaosGame {

    private final List<AffineTransformation> affineTransformations;
    private final List<Transformation> transformations;

    public ChaosGame(List<AffineTransformation> affineTransformations, List<Transformation> transformations) {
        this.affineTransformations = affineTransformations;
        this.transformations = transformations;
    }

    /**
     * Выполняет генерацию точек.
     *
     * @param iterations    количество итераций
     */
    public void generate(Renderer renderer, int iterations) {
        Random random = ThreadLocalRandom.current();
        Point currentPoint = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
        for (int i = 0; i < iterations; i++) {
            // Выбираем случайную трансформацию
            AffineTransformation affineTransformation = affineTransformations.get(random.nextInt(affineTransformations.size()));
            Transformation transformation = transformations.get(random.nextInt(transformations.size()));

            // Применяем трансформацию
            currentPoint = affineTransformation.transform(currentPoint);
            currentPoint = transformation.transform(currentPoint);
            renderer.renderPoint(currentPoint, getPointColor(currentPoint));
        }
    }

    private Color getPointColor(Point point) {
        double r2 = point.x() * point.x() + point.y() * point.y();
        int r = (int) (255 * Math.abs(Math.sin(r2))); // Основан на расстоянии
        int g = (int) (255 * Math.abs(Math.sin(point.x() * Math.PI))); // Зависит от координаты x
        int b = (int) (255 * Math.abs(Math.sin(point.y() * Math.PI))); // Цвет на основе оси X

        return new Color(r, g, b);
    }
}
