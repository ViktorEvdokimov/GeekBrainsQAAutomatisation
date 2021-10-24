package HW6;

public class Dog extends Animal {


    public Dog(String name, int swimDistance, int ranDistance) {
        super(name, swimDistance, ranDistance);
    }

    @Override
    public void ran(int distance) {
        if (distance > ranDistance) System.out.println("Dog " + name + " can`t ran to " + distance + "m.");
        else System.out.println("Dog " + name + " ran " + distance + "m.");
    }

    @Override
    public void swim(int distance) {
        if (distance > swimDistance) System.out.println("Dog " + name + " can`t swim to " + distance + "m.");
        else System.out.println("Dog " + name + " swim " + distance + "m.");
    }
}