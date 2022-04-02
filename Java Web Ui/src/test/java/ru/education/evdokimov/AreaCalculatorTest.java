package ru.education.evdokimov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AreaCalculatorTest {

    private static final Logger logger
            = LoggerFactory.getLogger(AreaCalculatorTest.class);

    private static Stream<Arguments> positiveTests() {
        return Stream.of(
                Arguments.of(2, 2, 3, 1.984313483298443, "Непрвльино рассчитан равнобедренный треугольник"), //
                Arguments.of(3, 3, 3, 3.897114317029974, "Непрвльино рассчитан равносторонний треугольник"),  //
                Arguments.of(3, 4, 5, 6, "Непрвльино рассчитан прямоугольный треугольник"), //
                Arguments.of(2, 3, 4, 2.9047375096555625, "Непрвльино рассчитан тупоугольный треугольник"), //
                Arguments.of(66, 67, 68, 1942.927800382711, "Непрвльино рассчитан остроугольный треугольник"),  //
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1996918625907477500.00,
                        "Непрвльино рассчитана площидь при вводе максимальных значений") //
        );
    }

    @ParameterizedTest
    @DisplayName("Расчет площади различных треугольников")
    @MethodSource
    public void positiveTests(int a, int b, int c, double S, String message) {
        assertEquals(S, AreaCalculator.calculateTriangleArea(a, b, c), message);
        logger.info("Расчет площади различных треугольников с параметрами " + a + ", " + b + ", " + c + " прошел успешно");
    }

    private static Stream<Arguments> invalidValuesTest() {
        return Stream.of(Arguments.of(0, 2, 3, "если a = 0"),
                Arguments.of(-1, 2, 3, "если a = -1"),
                Arguments.of(2, 0, 3, "если b = 0"),
                Arguments.of(2, -1, 3, "если b = -1"),
                Arguments.of(2, 2, 0, "если c = 0"),
                Arguments.of(2, 2, -1, "если c = -1"),
                Arguments.of(0, 0, 0, "если все числа = 0"),
                Arguments.of(-1, -1, -1, "если все числа = -1")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка реакции на невалидные значения")
    @MethodSource
    public void invalidValuesTest(int a, int b, int c, String message ) {
        String expectedMessage = "Введенные числа должны быть положительными";

        Throwable exc = assertThrows(IllegalArgumentException.class, () -> {
            AreaCalculator.calculateTriangleArea(a, b, c);
        }, "Не возникает ошибка " + message);
        assertEquals(expectedMessage, exc.getMessage());
        logger.info("Проверка реакции на невалидные значения с параметрами " + a + ", " + b + ", " + c + " прошел успешно");
    }

    private static Stream<Arguments> notTriangle() {
        return Stream.of(Arguments.of(10, 2, 3, "если a > b + c"),
                Arguments.of(2, 10, 3, "если b > c + b"),
                Arguments.of(2, 2, 10, "если c > a +b")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка если введенные числа не являются треугольником")
    @MethodSource
    public void notTriangle(int a, int b, int c, String message ) {
        String expectedMessage = "Введенный набор чисел не явзяется треугольником, поскольку одна из сторон больше суммы двух других";

        Throwable exc = assertThrows(IllegalArgumentException.class, () -> {
            AreaCalculator.calculateTriangleArea(a, b, c);
        }, "Не возникает ошибка " + message);
        assertEquals(expectedMessage, exc.getMessage());
        logger.info("Проверка если введенные числа не являются треугольником с параметрами " + a + ", " + b + ", " + c + " прошел успешно");
    }
}
