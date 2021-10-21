package HW5;

public class Employee {

    String fullName;
    String position;
    String email;
    String phoneNumber;
    int salary;
    int age;

    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String toString (){
        return String.format("%s %d years old,\n\tPosition: %s have salary %d,\n\tEmail: %s,\n\tPhone number: %s\n\t",
                fullName, age, position, salary, email, phoneNumber);
    }

    public void printInfo (){
        System.out.println(this);
    }
}
