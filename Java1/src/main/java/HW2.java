import main.java.HomeWorkApp;

public class HW2 {
    /**
     1. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20
     (включительно), если да – вернуть true, в противном случае – false.

     2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
     положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.

     3. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число
     отрицательное, и вернуть false если положительное.

     4. Написать метод, которому в качестве аргументов передается строка и число, метод должен отпечатать в консоль
     указанную строку, указанное количество раз;

     5. * Написать метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true, не
     високосный - false). Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     **/

    public static void main(String[] args) {
        System.out.println(isSumOfNumbersBetween10And20Inclusive(10, 2));
        printPositiveOreNegative(0);
        printStringNTimes("message", 2);
        System.out.println(isYearLeap(2400));
    }

    //task 1
    private static boolean isSumOfNumbersBetween10And20Inclusive (int a, int b){
        int sum = a + b;
        return sum >=10 && sum <=20;
    }

    //task 2
    private static void printPositiveOreNegative(int number){
        if(isNumberPositive(number)) System.out.println("положительное");
        else System.out.println("отрицательное");
    }

    //task 3
    private static boolean isNumberPositive(int number) {
        return number >= 0;
    }

    // task 4
    private static void printStringNTimes (String string, int countOfPrints){
        for (int i = 0; i < countOfPrints; i++) {
            System.out.println(string);
        }
    }

    // task 5
    private static boolean isYearLeap (int year){
        return (year % 4 == 0) && !((year % 100 == 0) && (year % 400 != 0));
    }
}
