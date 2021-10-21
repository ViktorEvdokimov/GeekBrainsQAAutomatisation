package HW5;

public class HW5 {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5]; // Вначале объявляем массив объектов
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Petrov Sergey", "Engineer", "Petrov@mailbox.com", "892312312", 50000, 30);
        employees[2] = new Employee("Frolova Olga", "Secretary", "Frolova@mailbox.com", "892312312", 40000, 20);
        employees[3] = new Employee("Kuznecov Oleg", "Storekeeper ", "Kuznecov@mailbox.com", "892312312", 20000, 55);
        employees[4] = new Employee("Bonk Anna", "Bookkeeper", "Bonk@mailbox.com", "892312312", 50000, 45);

        for (Employee employee : employees) {
            if(employee.age >= 40) employee.printInfo();
        }
    }
}
