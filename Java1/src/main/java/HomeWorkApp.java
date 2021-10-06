package main.java;

public class HomeWorkApp {

    /**
     * 1. Создать пустой проект в IntelliJ IDEA, создать класс HomeWorkApp, и прописать в нем метод main().
     *
     * 2. Создайте метод printThreeWords(), который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple.
     * Orange
     * Banana
     * Apple
     *
     * 3. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми
     * значениями, которыми захотите. Далее метод должен просуммировать эти переменные, и если их сумма больше или равна
     * 0, то вывести в консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”;
     *
     * 4. Создайте метод printColor() в теле которого задайте int переменную value и инициализируйте ее любым значением.
     * Если value меньше 0 (0 включительно), то в консоль метод должен вывести сообщение “Красный”, если лежит в пределах
     * от 0 (0 исключительно) до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;
     *
     * 5. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми
     * значениями, которыми захотите. Если a больше или равно b, то необходимо вывести в консоль сообщение “a >= b”, в
     * противном случае “a < b”;
     *
     * Методы из пунктов 2-5 вызовите из метода main() и посмотрите корректно ли они работают.
     */

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    public static void printThreeWords(){
        System.out.print("Orange\nBanana\nApple\n");
    }

    public static void checkSumSign(){
        int a = getRandomInt();
        int b = getRandomInt();
        System.out.printf("a = %d, b = %d\n", a, b);
        if((a+b)<0) System.out.println("Сумма отрицательная");
        else System.out.println("Сумма положительная");
    }

    public static void printColor(){
        int value = getRandomInt();
        System.out.println("value = " + value);
        if(value <= 0) System.out.println("Красный");
        else if (value <=100) System.out.println("Желтый");
        else System.out.println("Зеленый");
    }

    public static void compareNumbers(){
        int a = getRandomInt();
        int b = getRandomInt();
        System.out.printf("a = %d, b = %d\n", a, b);
        if(a >= b) System.out.println("a >= b");
        else System.out.println("a < b");
    }

    private static int getRandomInt (){
        return (int) (Math.random()*500-250);
    }
}
