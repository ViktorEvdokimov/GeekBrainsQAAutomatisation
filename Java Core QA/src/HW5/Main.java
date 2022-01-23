package HW5;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
    }

    /**
     Реализовать сохранение данных в csv файл;
     */
    private static void task1(){
        String[] header = new String[]{"Value 1","Value 2","Value 3"};
        int[] data1 = new int[]{100,200,123};
        int[] data2 = new int[]{300,400,500};
        int[] data3 = new int[]{12,55,502340};
        AppData appData = new AppData(header, data1, data2, data3, data1);
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("resources\\HW5\\file.csv"))) {
            stream.write(appData.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Реализовать загрузку данных из csv файла. Файл читается целиком.
     Структура csv файла:
     | Строка заголовок с набором столбцов |
     | Набор строк с целочисленными значениями |
     | * Разделитель между столбцами - символ точка с запятой (;) |
     */
    private static void task2(){
        AppData appData = new AppData();
        try (BufferedReader stream = new BufferedReader(new FileReader("resources\\HW5\\file.csv"))){
            String line = stream.readLine();
            appData.setHeader(line.split(";"));
            line = stream.readLine();
            while (line != null){
                appData.addData(line);
                line = stream.readLine();
            }
            System.out.println(appData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
