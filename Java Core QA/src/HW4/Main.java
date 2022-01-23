package HW4;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        task1 ();
        task2 ();
    }


    /**
      1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных
      слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
     */
    private static void task1 (){
        System.out.println(" ----- Task 1     -----");
        String[] wordsArray = new String[]{"Создать", "массив", "с", "набором", "слов", "10-20", "слов", "должны",
                "встречаться", "повторяющиеся", "Найти", "и", "вывести", "список", "уникальных", "слов",
                "из", "которых", "состоит", "массив", "дубликаты", "не", "считаем", "Посчитать", "сколько", "раз",
                "встречается", "каждое", "слово"};
        countAndPrintUniqueWords(wordsArray);
        System.out.println(" ----- Task 1 end -----");
    }

    private static void countAndPrintUniqueWords (String[] words){
        Map<String, Integer> wordsCount = new HashMap();
        for (String word : words) {
            word = word.toLowerCase();
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }
        System.out.println(wordsCount.toString());
    }

    /**
     2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.
     В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер
     телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
     тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    private static void task2 (){
        System.out.println(" ----- Task 2     -----");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(79595556611L, "Ivanov");
        phoneBook.add(79595556642L, "Petrov");
        phoneBook.add(79595556643L, "Sidorov");
        phoneBook.add(79595556644L, "Kuznetsov");
        phoneBook.add(79595556645L, "Ivanov");
        System.out.println(phoneBook.get("Petrov"));
        System.out.println(phoneBook.get("Ivanov"));
        System.out.println(" ----- Task 2 end -----");
    }

}
