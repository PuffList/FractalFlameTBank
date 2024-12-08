package backend.academy.app;

import backend.academy.transformation.BentTransformation;
import backend.academy.transformation.ExTransformation;
import backend.academy.transformation.ExponentialTransformation;
import backend.academy.transformation.EyefishTrasformation;
import backend.academy.transformation.HandkerchiefTransformation;
import backend.academy.transformation.PolarTransformation;
import backend.academy.transformation.PowerTransformation;
import backend.academy.transformation.SphericalTransformation;
import backend.academy.transformation.SpiralTransformation;
import backend.academy.transformation.SwirlTransformation;
import backend.academy.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для обработки ввода пользователя.
 * Содержит методы для получения различных типов данных (int, boolean, String) и списка трансформаций.
 */
@UtilityClass
public class UserInputHandler {

    private static final String SWIRL = "swirl";
    private static final String POLAR = "polar";
    private static final String EYEFISH = "eyefish";
    private static final String HANDKERCHIEF = "handkerchief";
    private static final String SPHERICAL = "spherical";
    private static final String SPIRAL = "spiral";
    private static final String BENT = "bent";
    private static final String EXPONENTIAL = "exponential";
    private static final String EX = "ex";
    private static final String POWER = "power";

    /**
     * Получает целое число от пользователя. Если введено пустое значение, возвращается значение по умолчанию.
     *
     * @param scanner Scanner для получения ввода пользователя.
     * @param defaultValue Значение по умолчанию.
     * @return Введённое число или значение по умолчанию.
     */
    public static int getInt(Scanner scanner, int defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : Integer.parseInt(input);
    }

    /**
     * Получает логическое значение от пользователя. Если введено пустое значение, возвращается значение по умолчанию.
     *
     * @param scanner Scanner для получения ввода пользователя.
     * @param defaultValue Значение по умолчанию.
     * @return Введённое логическое значение или значение по умолчанию.
     */
    public static boolean getBoolean(Scanner scanner, boolean defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : Boolean.parseBoolean(input);
    }

    /**
     * Получает строку от пользователя. Если введено пустое значение, возвращается значение по умолчанию.
     *
     * @param scanner Scanner для получения ввода пользователя.
     * @param defaultValue Значение по умолчанию.
     * @return Введённая строка или значение по умолчанию.
     */
    public static String getString(Scanner scanner, String defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : input;
    }

    /**
     * Получает список трансформаций от пользователя. Если введено пустое значение, возвращается список по умолчанию.
     *
     * @param scanner Scanner для получения ввода пользователя.
     * @return Список трансформаций.
     */
    public static List<String> getTransformations(Scanner scanner) {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return List.of(SWIRL, SPHERICAL, HANDKERCHIEF, BENT, POWER, EYEFISH, EXPONENTIAL, EX, POLAR, SPIRAL);
        }
        String[] transformations = input.split(",");
        List<String> result = new ArrayList<>();
        for (String t : transformations) {
            result.add(t.trim());
        }
        return result;
    }

    /**
     * Создаёт список объектов трансформаций на основе их имён.
     *
     * @param names Список имён трансформаций.
     * @return Список объектов трансформаций.
     * @throws IllegalArgumentException Если трансформация не найдена.
     */
    public static List<Transformation> createTransformations(List<String> names) {
        List<Transformation> transformations = new ArrayList<>();
        for (String name : names) {
            switch (name.toLowerCase()) {
                case SPHERICAL -> transformations.add(new SphericalTransformation());
                case HANDKERCHIEF -> transformations.add(new HandkerchiefTransformation());
                case BENT -> transformations.add(new BentTransformation());
                case POWER -> transformations.add(new PowerTransformation());
                case SWIRL -> transformations.add(new SwirlTransformation());
                case EYEFISH -> transformations.add(new EyefishTrasformation());
                case EXPONENTIAL -> transformations.add(new ExponentialTransformation());
                case EX -> transformations.add(new ExTransformation());
                case POLAR -> transformations.add(new PolarTransformation());
                case SPIRAL -> transformations.add(new SpiralTransformation());
                default -> throw new IllegalArgumentException("Неизвестная трансформация: " + name);
            }
        }
        return transformations;
    }
}
