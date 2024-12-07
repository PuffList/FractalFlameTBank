package backend.academy.app;

import backend.academy.domain.Config;
import backend.academy.engine.SingleThreadedFlameGenerator;
import backend.academy.render.Renderer;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.Scanner;

public class App {

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Генерация фрактального пламени ===");

            // Ввод параметров пользователем
            System.out.print("Введите ширину изображения (по умолчанию 1920): ");
            int width = UserInputHandler.getInt(scanner, 1920);

            System.out.print("Введите высоту изображения (по умолчанию 1080): ");
            int height = UserInputHandler.getInt(scanner, 1080);

            System.out.print("Введите количество итераций (по умолчанию 10 000 000): ");
            int iterations = UserInputHandler.getInt(scanner, 10_000_000);

            System.out.print("Запускать в многопоточном режиме? (true/false, по умолчанию false): ");
            boolean multithreaded = UserInputHandler.getBoolean(scanner, false);

            System.out.print("Введите количество потоков (если многопоточный режим, по умолчанию 4): ");
            int threads = multithreaded ? UserInputHandler.getInt(scanner, 4) : 1;

            System.out.print("Введите имя выходного файла (по умолчанию flame.png): ");
            String outputFile = UserInputHandler.getString(scanner, "src/main/resources/flame.png");

            System.out.println("Выберите трансформации (Linear, Sinusoidal, Spherical, Swirl, ): ");
            List<String> transformationNames = UserInputHandler.getTransformations(scanner);

            // Создание конфигурации
            Config config =
                new Config(width, height, iterations, transformationNames, multithreaded, threads, outputFile);

            // Рендеринг
            Renderer renderer = new Renderer(config.width(), config.height(), config.outputFile());

            List<Transformation> transformations = UserInputHandler.createTransformations(transformationNames);

            if (config.multithreaded()) {
                // Многопоточная версия
                System.out.println("Многопоточная версия пока не реализована.");
            } else {
                SingleThreadedFlameGenerator generator =
                    new SingleThreadedFlameGenerator(config, transformations, renderer);
                generator.generate();
            }

            System.out.println("Фрактал успешно сгенерирован и сохранён в " + outputFile);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
