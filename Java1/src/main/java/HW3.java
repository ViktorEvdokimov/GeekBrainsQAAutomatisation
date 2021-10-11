import java.util.Arrays;

public class HW3 {
    /**
     1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью
     цикла и условия заменить 0 на 1, 1 на 0;
     2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
     3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
     цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
     Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то
     есть [0][0], [1][1], [2][2], …, [n][n];
     5. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int
     длиной len, каждая ячейка которого равна initialValue;
     6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
     7. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
     если в массиве есть место, в котором сумма левой и правой части массива равны.
     **Примеры:
     checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
     checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1

     граница показана символами |||, эти символы в массив не входят и не имеют никакого отношения к ИЛИ.

     8. *** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или
     отрицательным), при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично.
     Для усложнения задачи нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один
     вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону
     сдвиг можете выбирать сами.
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(invert0and1inArray(0, 1, 0, 1, 0, 0, 0, 1)) + "\n");

        System.out.println(Arrays.toString(getArrayOfConsecutiveNumbers(1, 100)) + "\n");

        int[] array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyNumbersLessThenConstantInArray(2, 6, array);
        System.out.println(Arrays.toString(array) + "\n");

        getAndPrintTwoDimensionalArrayWithDiagonalsAs1(5);
        System.out.println("");

        System.out.println(Arrays.toString(getArrayWithAllElementEqualsValue(5, -1)) + "\n");

        array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printMinAndMaxValueFromArray(array);
        System.out.println("");

        System.out.println(isArrayHaveBalancePlace(1, 1, 1, 2, 1) + "\n");


        System.out.println(Arrays.toString(getArrayWithDisplacedElementsInArray(array, -2)) + "\n");
    }

    private static int[] invert0and1inArray (int ... inputArray){
        int[] outputArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 0) outputArray[i] = 1;
            else if (inputArray[i] == 1) outputArray[i] = 0;
            else throw new RuntimeException("You should to enter 0 or 1");
        }
        return outputArray;
    }

    private static int[] getArrayOfConsecutiveNumbers (int minNumber, int maxNumber){
        if (minNumber < maxNumber) {
            int[] result = new int[maxNumber - minNumber + 1];
            for(int i = minNumber; i <= maxNumber; i++){
                result[i - minNumber] = i;
            }
            return result;
        } else throw new RuntimeException("maxNumber should be greater then minNumber");
    }

    private static void multiplyNumbersLessThenConstantInArray (int multiplyTo, int constant, int ...array){
        for (int i = 0; i < array.length; i++) {
            if(array[i] < constant) array[i] *= multiplyTo;
        }
    }

    private static int[][] getAndPrintTwoDimensionalArrayWithDiagonalsAs1 (int arrayLength){
        int[][] result = new int[arrayLength][arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength; j++) {
                if(i == j || (i + j + 1) == arrayLength) result[i][j] = 1;
                System.out.print(result[i][j] + "  ");
            }
            System.out.println("");
        }
        return result;
    }

    private static int[] getArrayWithAllElementEqualsValue (int len, int initialValue){
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = initialValue;
        }
        return result;
    }

    private static void printMinAndMaxValueFromArray (int[] array){
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if(i > max) max = i;
            if(i < min) min = i;
        }
        System.out.printf("min = %d, max = %d\n", min, max);
    }

    private static boolean isArrayHaveBalancePlace (int ... array){
        for (int i = 0; i < array.length; i++) {
            int leftSum = 0;
            int wrightSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += array[j];
            }
            for (int j = i; j < array.length; j++) {
                wrightSum += array[j];
            }
            if(leftSum == wrightSum) return true;
        }
        return false;
    }

    private static int[] getArrayWithDisplacedElementsInArray (int[] array, int displaceValue){
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int newPosition = (i + (displaceValue % array.length) + array.length) % (array.length);
            result[newPosition] = array[i];
        }
        return result;
    }
}
