package backend.academy.domain;

import java.util.List;

public record Config(int width,
                     int height,
                     int iterations,
                     int samplesCount,
                     int affineTransformationsCount,
                     List<String> transformations,
                     boolean multithreaded,
                     String outputFile,
                     int axesCount) {}
