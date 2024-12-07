package backend.academy.domain;

import java.util.List;

public record Config(int width,
                     int height,
                     int iterations,
                     List<String> transformations,
                     boolean multithreaded,
                     int threads,
                     String outputFile) {}
