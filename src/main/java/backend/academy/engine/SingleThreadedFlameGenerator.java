package backend.academy.engine;

import backend.academy.domain.Config;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.List;

/**
 * Класс для однопоточной генерации пламени.
 */
public class SingleThreadedFlameGenerator extends BaseFlameGenerator {

    private static final PrintStream OUT = System.out;

    /**
     * Конструктор для одно поточной генерации пламени.
     *
     * @param config конфигурация генератора пламени
     * @param transformations список применяемых трансформаций
     * @param renderer рендерер для отрисовки изображения
     */
    public SingleThreadedFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        super(config, transformations, renderer);
    }

    /**
     * Генерирует пламя в одном потоке.
     */
    @Override
    public void generateFlame() {
        OUT.println("Запуск генерации...");

        for (int i = 0; i < config.samplesCount(); i++) {
            generateFlameSample();
        }

        renderer.gammaCorrection();
        renderer.renderImage();
        renderer.saveImage(config.outputFile());
        OUT.println("\nГенерация завершена!");
    }
}
