package backend.academy.engine;

import backend.academy.domain.Config;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedFlameGenerator extends BaseFlameGenerator {

    private static final PrintStream OUT = System.out;
    private static final PrintStream ERR = System.err;
    private final int THREADS_COUNT = 4;

    public MultiThreadedFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        super(config, transformations, renderer);
    }

    @Override
    public void generateFlame() throws InterruptedException {
        OUT.println("Запуск многопоточной генерации...");

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        for (int i = 0; i < config.samplesCount(); i++) {
            executorService.execute(this::generateFlameSample);
        }
        executorService.shutdown();
        if(!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
            ERR.println("\nГенерация не завершилаксь за 100 секунд.");
        }
        renderer.gammaCorrection();
        renderer.renderImage();

        renderer.saveImage(config.outputFile());
        OUT.println("\nГенерация завершена!");
    }
}
