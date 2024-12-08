package backend.academy.transformation;

import backend.academy.domain.Point;

/**
 * Интерфейс для преобразования точки.
 * Все преобразования должны реализовывать метод transform.
 */
public interface Transformation  {

    /**
     * Преобразует точку в новую точку с учетом преобразования.
     *
     * @param p точка для преобразования
     * @return преобразованная точка
     */
    Point transform(Point p);
}
