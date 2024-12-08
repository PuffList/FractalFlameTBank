package backend.academy.engine;

import backend.academy.domain.Config;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;

import java.io.PrintStream;
import java.util.List;

public class SingleThreadedFlameGenerator extends BaseFlameGenerator {

    private static final PrintStream OUT = System.out;

    public SingleThreadedFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        super(config, transformations, renderer);
    }

    @Override
    public void generateFlame() {
        OUT.println("Запуск генерации...");
        for (int i = 0; i < config.samplesCount(); i++) {
            generateFlameSample();
        }
        renderer.gammaCorrection();
        renderer.renderImage();
        // Финальное сохранение изображения
        renderer.saveImage(config.outputFile());
        OUT.println("\nГенерация завершена!");
    }
}
