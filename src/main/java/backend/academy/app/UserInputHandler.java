package backend.academy.app;

import backend.academy.transformation.BentTransformation;
import backend.academy.transformation.HandkerchiefTransformation;
import backend.academy.transformation.PowerTransformation;
import backend.academy.transformation.SphericalTransformation;
import backend.academy.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputHandler {

    public static int getInt(Scanner scanner, int defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : Integer.parseInt(input);
    }

    public static boolean getBoolean(Scanner scanner, boolean defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : Boolean.parseBoolean(input);
    }

    public static String getString(Scanner scanner, String defaultValue) {
        String input = scanner.nextLine();
        return input.isEmpty() ? defaultValue : input;
    }

    public static List<String> getTransformations(Scanner scanner) {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return List.of("spherical", "handkerchief", "bent", "power"); // Значения по умолчанию
        }
        String[] transformations = input.split(",");
        List<String> result = new ArrayList<>();
        for (String t : transformations) {
            result.add(t.trim());
        }
        return result;
    }

    public static List<Transformation> createTransformations(List<String> names) {
        List<Transformation> transformations = new ArrayList<>();
        for (String name : names) {
            switch (name.toLowerCase()) {
                case "spherical" -> transformations.add(new SphericalTransformation());
                case "handkerchief" -> transformations.add(new HandkerchiefTransformation());
                case "bent" -> transformations.add(new BentTransformation());
                case "power" -> transformations.add(new PowerTransformation());
                default -> throw new IllegalArgumentException("Неизвестная трансформация: " + name);
            }
        }
        return transformations;
    }
}
