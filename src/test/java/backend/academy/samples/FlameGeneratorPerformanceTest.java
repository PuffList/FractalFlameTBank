package backend.academy.samples;

import backend.academy.domain.Config;
import backend.academy.engine.MultiThreadedFlameGenerator;
import backend.academy.engine.SingleThreadedFlameGenerator;
import backend.academy.render.Renderer;
import backend.academy.transformation.SwirlTransformation;
import backend.academy.transformation.Transformation;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlameGeneratorPerformanceTest {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ITERATIONS = 1000;
    private static final int SAMPLES_COUNT = 10;
    private static final int AFFINE_TRANSFORMATIONS_COUNT = 5;
    private static final int AXES_COUNT = 8;
    private static final String OUTPUT_FILE = "output.png";

    @Test
    public void testPerformanceComparison() throws InterruptedException {

        Config config = new Config(WIDTH, HEIGHT, ITERATIONS, SAMPLES_COUNT, AFFINE_TRANSFORMATIONS_COUNT,
            Arrays.asList("swirl"), true, OUTPUT_FILE, AXES_COUNT);
        List<Transformation> transformations = Arrays.asList(new SwirlTransformation());
        Renderer renderer = new Renderer(WIDTH, HEIGHT, AXES_COUNT, OUTPUT_FILE);
        long singleThreadStart = System.currentTimeMillis();
        SingleThreadedFlameGenerator
            singleThreadedFlameGenerator = new SingleThreadedFlameGenerator(config, transformations, renderer);
        singleThreadedFlameGenerator.generateFlame();
        long singleThreadEnd = System.currentTimeMillis();
        long singleThreadDuration = singleThreadEnd - singleThreadStart;
        System.out.println("Время выполнения однопоточной генерации: " + singleThreadDuration + " миллисекунд");
        long multiThreadStart = System.currentTimeMillis();
        MultiThreadedFlameGenerator
            multiThreadedFlameGenerator = new MultiThreadedFlameGenerator(config, transformations, renderer);
        multiThreadedFlameGenerator.generateFlame();
        long multiThreadEnd = System.currentTimeMillis();
        long multiThreadDuration = multiThreadEnd - multiThreadStart;
        System.out.println("Время выполнения многопоточной генерации: " + multiThreadDuration + " миллисекунд");
        assertTrue(multiThreadDuration < singleThreadDuration, "Многопоточная версия должна быть быстрее");
    }
}
