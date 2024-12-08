package backend.academy.engine;

import backend.academy.domain.Color;
import backend.academy.domain.Point;
import backend.academy.render.Renderer;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс, реализующий саму игру хаоса для генерации фрактальных изображений.
 * Применяет случайные трансформации к точкам и отрисовывает их.
 */
public class ChaosGame {

    private final int COLOR_RESTRICTED = 255;
    private final List<AffineTransformation> affineTransformations;
    private final List<Transformation> transformations;

    /**
     * Конструктор для инициализации игры хаоса с заданными трансформациями.
     *
     * @param affineTransformations список аффинных трансформаций
     * @param transformations список других трансформаций
     */
    public ChaosGame(List<AffineTransformation> affineTransformations, List<Transformation> transformations) {
        this.affineTransformations = affineTransformations;
        this.transformations = transformations;
    }

    /**
     * Генерирует точки, применяя случайные трансформации, и отрисовывает их.
     *
     * @param renderer рендерер для отображения точек
     * @param iterations количество итераций для генерации точек
     */
    public void generate(Renderer renderer, int iterations) {
        Random random = ThreadLocalRandom.current();
        Point currentPoint = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);

        for (int i = 0; i < iterations; i++) {
            AffineTransformation affineTransformation =
                    affineTransformations.get(random.nextInt(affineTransformations.size()));
            Transformation transformation = transformations.get(random.nextInt(transformations.size()));
            currentPoint = affineTransformation.transform(currentPoint);
            currentPoint = transformation.transform(currentPoint);
            renderer.renderPoint(currentPoint, getPointColor(currentPoint));
        }
    }

    private Color getPointColor(Point point) {
        double r2 = point.x() * point.x() + point.y() * point.y();
        int r = (int) (COLOR_RESTRICTED * Math.abs(Math.sin(r2)));
        int g = (int) (COLOR_RESTRICTED * Math.abs(Math.sin(point.x() * Math.PI)));
        int b = (int) (COLOR_RESTRICTED * Math.abs(Math.sin(point.y() * Math.PI)));

        return new Color(r, g, b);
    }
}
