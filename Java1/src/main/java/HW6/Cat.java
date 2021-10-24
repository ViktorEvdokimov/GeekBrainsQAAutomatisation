package HW6;

public class Cat extends Animal{


    public Cat(String name, int swimDistance, int ranDistance) {
        super(name, swimDistance, ranDistance);
    }

    @Override
    public void ran(int distance) {
        if(distance > ranDistance) System.out.println("Cat " + name + " can`t ran to " + distance + "m.");
        else System.out.println("Cat " + name + " ran " + distance + "m.");
    }

    @Override
    public void swim(int distance) {
        System.out.println("Cats can`t swim");
    }
}
