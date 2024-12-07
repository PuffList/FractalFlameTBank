package backend.academy.engine;

import backend.academy.app.ProgressBar;
import backend.academy.domain.Color;
import backend.academy.domain.Config;
import backend.academy.domain.Point;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;

import java.util.List;
import java.util.Random;

public class SingleThreadedFlameGenerator {

    private final Config config;
    private final ChaosGame chaosGame;
    private final Renderer renderer;

    public SingleThreadedFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        this.config = config;
        this.chaosGame = new ChaosGame(transformations); // Используем ChaosGame
        this.renderer = renderer;
    }

    public void generate() {
        Random random = new Random();
        Point initialPoint = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
        ProgressBar progressBar = new ProgressBar(config.iterations());

        System.out.println("Запуск генерации...");

        chaosGame.generate(config.iterations(), initialPoint, point -> {
            double r2 = point.x() * point.x() + point.y() * point.y();
            int r = (int) (255 * Math.exp(-r2) % 255); // Цвет на основе расстояния
            int g = (int) (255 * (Math.abs(point.y()) % 1)); // Цвет на основе оси Y
            int b = (int) (255 * (Math.abs(point.x()) % 1)); // Цвет на основе оси X
            renderer.render(point, new Color(r, g, b));
            progressBar.increment();
        });

        //renderer.applyGammaCorrection(1.0);
        // Финальное сохранение изображения
        renderer.saveImage(config.outputFile());
        System.out.println("\nГенерация завершена!");
    }
}
