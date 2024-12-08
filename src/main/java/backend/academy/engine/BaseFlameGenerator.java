package backend.academy.engine;

import backend.academy.domain.Config;
import backend.academy.render.Renderer;
import backend.academy.transformation.AffineTransformation;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseFlameGenerator {

    protected final Config config;
    protected final ChaosGame chaosGame;
    protected final Renderer renderer;

    public BaseFlameGenerator(Config config, List<Transformation> transformations, Renderer renderer) {
        this.config = config;

        List<AffineTransformation> affineTransformations = IntStream.range(0, config.affineTransformationsCount())
            .mapToObj(x -> new AffineTransformation())
            .collect(Collectors.toList());

        this.chaosGame = new ChaosGame(affineTransformations, transformations);
        this.renderer = renderer;
    }

    protected void generateFlameSample() {
        chaosGame.generate(renderer, config.iterations());
    }

    protected abstract void generateFlame() throws InterruptedException;
}
