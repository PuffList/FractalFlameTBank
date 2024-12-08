package backend.academy.samples;

import backend.academy.domain.Point;
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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationsTest {

    private static final double DELTA = 0.0001;

    @Test
    void testBentTransformation() {
        Transformation transformation = new BentTransformation();
        Point input = new Point(1, -1);
        Point expected = new Point(1, -0.5);
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(-2, 2);
        expected = new Point(-4, 2);
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testExponentialTransformation() {
        Transformation transformation = new ExponentialTransformation();
        Point input = new Point(1, 1);
        Point expected = new Point(Math.exp(1 - 1) * Math.cos(Math.PI * 1), Math.exp(1 - 1) * Math.sin(Math.PI * 1));
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, 0.5);
        expected = new Point(Math.exp(2 - 1) * Math.cos(Math.PI * 0.5), Math.exp(2 - 1) * Math.sin(Math.PI * 0.5));
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testExTransformation() {
        Transformation transformation = new ExTransformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        double o = Math.atan(1.0 / 1.0);
        double p0 = Math.sin(o + r);
        double p1 = Math.cos(o - r);
        double expectedX = r * (Math.pow(p0, 3) + Math.pow(p1, 3));
        double expectedY = r * (Math.pow(p0, 3) - Math.pow(p1, 3));
        Point expected = new Point(expectedX, expectedY);
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, 3);
        r = Math.sqrt(2 * 2 + 3 * 3);
        o = Math.atan(2.0 / 3.0);
        p0 = Math.sin(o + r);
        p1 = Math.cos(o - r);
        expectedX = r * (Math.pow(p0, 3) + Math.pow(p1, 3));
        expectedY = r * (Math.pow(p0, 3) - Math.pow(p1, 3));
        expected = new Point(expectedX, expectedY);
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testEyefishTransformation() {
        Transformation transformation = new EyefishTrasformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        Point expected = new Point((2 * 1) / (r + 1), (2 * 1) / (r + 1));
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -2);
        r = Math.sqrt(2 * 2 + (-2) * (-2));
        expected = new Point((2 * 2) / (r + 1), (2 * -2) / (r + 1));
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testHandkerchiefTransformation() {
        Transformation transformation = new HandkerchiefTransformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        double theta = Math.atan2(1, 1);
        Point expected = new Point(r * Math.sin(theta + r), r * Math.cos(theta - r));
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -3);
        r = Math.sqrt(2 * 2 + (-3) * (-3));
        theta = Math.atan2(-3, 2);
        expected = new Point(r * Math.sin(theta + r), r * Math.cos(theta - r));
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testPolarTransformation() {
        Transformation transformation = new PolarTransformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        double o = Math.atan(1.0 / 1.0);
        Point expected = new Point(o / Math.PI, r - 1);
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, 2);
        r = Math.sqrt(2 * 2 + 2 * 2);
        o = Math.atan(2.0 / 2.0);
        expected = new Point(o / Math.PI, r - 1);
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testPowerTransformation() {
        Transformation transformation = new PowerTransformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        double theta = Math.atan2(1, 1);
        double pow = Math.pow(r, Math.sin(theta));
        Point expected = new Point(pow * Math.cos(theta), pow * Math.sin(theta));
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -3);
        r = Math.sqrt(2 * 2 + (-3) * (-3));
        theta = Math.atan2(-3, 2);
        pow = Math.pow(r, Math.sin(theta));
        expected = new Point(pow * Math.cos(theta), pow * Math.sin(theta));
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testSphericalTransformation() {
        Transformation transformation = new SphericalTransformation();
        Point input = new Point(1, 1);
        double r2 = 1 * 1 + 1 * 1;
        Point expected = new Point(1 / r2, 1 / r2);
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -2);
        r2 = 2 * 2 + (-2) * (-2);
        expected = new Point(2 / r2, -2 / r2);
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testSpiralTransformation() {
        Transformation transformation = new SpiralTransformation();
        Point input = new Point(1, 1);
        double r = Math.sqrt(1 * 1 + 1 * 1);
        double o = Math.atan(1.0 / 1.0);
        Point expected = new Point((Math.cos(o) + Math.sin(r)) / r, (Math.sin(o) - Math.cos(r)) / r); // ожидаемый результат
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -2);
        r = Math.sqrt(2 * 2 + (-2) * (-2));
        o = Math.atan(2.0 / -2.0);
        expected = new Point((Math.cos(o) + Math.sin(r)) / r, (Math.sin(o) - Math.cos(r)) / r);
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }

    @Test
    void testSwirlTransformation() {
        Transformation transformation = new SwirlTransformation();
        Point input = new Point(1, 1);
        double r2 = 1 * 1 + 1 * 1;
        Point expected = new Point(
            1 * Math.sin(r2) - 1 * Math.cos(r2),
            1 * Math.cos(r2) + 1 * Math.sin(r2)
        );
        Point result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);

        input = new Point(2, -2);
        r2 = 2 * 2 + (-2) * (-2);
        expected = new Point(
            2 * Math.sin(r2) - (-2) * Math.cos(r2),
            2 * Math.cos(r2) + (-2) * Math.sin(r2)
        );
        result = transformation.transform(input);

        assertEquals(expected.x(), result.x(), DELTA);
        assertEquals(expected.y(), result.y(), DELTA);
    }
}
