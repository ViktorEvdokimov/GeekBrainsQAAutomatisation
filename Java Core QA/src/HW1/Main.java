package HW1;

import HW1.Course.Course;

public class Main {

    public static void main(String[] args) {
        Course c = new Course(5); // Создаем полосу препятствий
        System.out.println(c);
        Team team = new Team("Суслики", 10); // Создаем команду
        team.startRace(c);
    }
}
