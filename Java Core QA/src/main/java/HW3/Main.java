package HW3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main<T> {

    public static void main(String[] args) {
        task1();
        System.out.println();
        task2();
    }


    /**
     * Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
     */
    private static void task1(){
        Main<Object> main = new Main<>();
        Object[] arr = new Object[]{1L, "string", 'q', 0.6f, 0, new ArrayList<>()};
        main.replaceElementsInArray(arr, 0, 3);
        System.out.println(Arrays.toString(arr));
    }

    private void replaceElementsInArray(T[] array, int firstElementNumber, int secondElementNumber){
        if (firstElementNumber < 0 || secondElementNumber < 0 ||
                firstElementNumber + 1 > array.length || secondElementNumber + 1 > array.length){
        }
        System.err.println("Incorrect element number");
        T tmp = array[firstElementNumber];
        array[firstElementNumber] = array[secondElementNumber];
        array[secondElementNumber] = tmp;
    }

    /**
     * Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
     * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку
     * нельзя сложить и яблоки, и апельсины;
     * Для хранения фруктов внутри коробки можно использовать ArrayList;
     * Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока –
     * 1.0f, апельсина – 1.5f (единицы измерения не важны);
     * Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в
     * compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать
     * коробки с яблоками и апельсинами;
     * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов:
     * нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую
     * перекидываются объекты, которые были в первой;
     * Не забываем про метод добавления фрукта в коробку.
     */

    private static void task2 (){
        Box<Orange> orangeBox = new Box<>(getOrangesList(10));
        Box<Apple> appleBox = new Box<>(getApplesList(10));
        System.out.println(orangeBox.compare(appleBox));
        System.out.println(appleBox.getWeight());
        Box<Apple> secondAppleBox = new Box<>(getApplesList(5));
        secondAppleBox.pourFruitsToAnotherBox(appleBox);
        System.out.println(orangeBox.compare(appleBox));
        System.out.println(appleBox.getWeight());
    }

    private static List<Orange> getOrangesList(int count){
        List<Orange> oranges = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            oranges.add(new Orange());
        }
        return oranges;
    }

    private static List<Apple> getApplesList(int count){
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            apples.add(new Apple());
        }
        return apples;
    }
}
