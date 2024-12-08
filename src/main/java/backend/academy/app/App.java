package backend.academy.app;

import backend.academy.domain.Config;
import backend.academy.engine.MultiThreadedFlameGenerator;
import backend.academy.engine.SingleThreadedFlameGenerator;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final PrintStream OUT = System.out;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PrintStream ERR = System.err;

    public void run() {
        try (SCANNER) {
            OUT.println("=== Генерация фрактального пламени ===");

            OUT.print("Введите ширину изображения (по умолчанию 1920): ");
            int width = UserInputHandler.getInt(SCANNER, 1920);

            OUT.print("Введите высоту изображения (по умолчанию 1080): ");
            int height = UserInputHandler.getInt(SCANNER, 1080);

            OUT.print("Введите количество итераций (по умолчанию 50 0000): ");
            int iterations = UserInputHandler.getInt(SCANNER, 500000);

            OUT.print("Введите количество начальных точек (по умолчанию 5): ");
            int samplesCount = UserInputHandler.getInt(SCANNER, 5);

            OUT.print("Введите количество аффинных преобразований (по умолчанию 5): ");
            int affineTransformationsCount = UserInputHandler.getInt(SCANNER, 5);

            OUT.print("Запускать в многопоточном режиме? (true/false, по умолчанию false): ");
            boolean multithreaded = UserInputHandler.getBoolean(SCANNER, false);

            OUT.print("Введите имя выходного файла (по умолчанию flame.png): ");
            String outputFile = UserInputHandler.getString(SCANNER, "src/main/resources/flame.png");

            OUT.println("Выберите трансформации (Spherical, Handkerchief, Bent, Power," +
                " Eyefish, Exponential, Ex, Polar, Spiral, Swirl): ");
            List<String> transformationNames = UserInputHandler.getTransformations(SCANNER);

            OUT.print("Введите количество осей симметрии (по умолчанию 1): ");
            int axesCount = UserInputHandler.getInt(SCANNER, 1);

            Config config =
                new Config(width, height, iterations,
                    samplesCount, affineTransformationsCount,
                    transformationNames, multithreaded, outputFile, axesCount);

            Renderer renderer = new Renderer(config.width(), config.height(), config.axesCount(), config.outputFile());

            List<Transformation> transformations = UserInputHandler.createTransformations(transformationNames);

            long startTime = System.currentTimeMillis();

            if (config.multithreaded()) {
                MultiThreadedFlameGenerator generator =
                    new MultiThreadedFlameGenerator(config, transformations, renderer);
                generator.generateFlame();
            } else {
                SingleThreadedFlameGenerator generator =
                    new SingleThreadedFlameGenerator(config, transformations, renderer);
                generator.generateFlame();
            }

            long endTime = System.currentTimeMillis();
            OUT.println("Время выполнения: " + (endTime - startTime) + " мс");


            OUT.println("Фрактал успешно сгенерирован и сохранён в " + outputFile);
        } catch (Exception e) {
            ERR.println("Ошибка: " + e.getMessage());
        }
    }
}
