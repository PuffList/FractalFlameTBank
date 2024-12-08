package backend.academy.engine;

import backend.academy.domain.Config;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Класс для многопоточной генерации пламени.
 * Использует пул потоков для параллельной генерации точек.
 */
public class MultiThreadedFlameGenerator extends BaseFlameGenerator {

    private static final PrintStream OUT = System.out;
    private static final PrintStream ERR = System.err;
    private static final int TIME_RESTRICTION = 100;
    private final int threads_count = 4;

    /**
     * Конструктор для многопоточной генерации пламени.
     *
     * @param config конфигурация генератора пламени
     * @param transformations список применяемых трансформаций
     * @param renderer рендерер для отрисовки изображения
     */
    public MultiThreadedFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        super(config, transformations, renderer);
    }

    /**
     * Генерирует пламя с использованием многопоточности.
     *
     * @throws InterruptedException если выполнение будет прервано
     */
    @Override
    public void generateFlame() throws InterruptedException {
        OUT.println("Запуск многопоточной генерации...");
        ExecutorService executorService = Executors.newFixedThreadPool(threads_count);

        for (int i = 0; i < config.samplesCount(); i++) {
            executorService.execute(this::generateFlameSample);
        }

        executorService.shutdown();

        if (!executorService.awaitTermination(TIME_RESTRICTION, TimeUnit.SECONDS)) {
            ERR.println("\nГенерация не завершилаксь за 100 секунд.");
        }

        renderer.gammaCorrection();
        renderer.renderImage();
        renderer.saveImage(config.outputFile());
        OUT.println("\nГенерация завершена!");
    }
}
