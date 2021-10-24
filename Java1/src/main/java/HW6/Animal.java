package HW6;

public abstract class Animal {

    protected String name;
    protected int swimDistance;
    protected int ranDistance;

    public Animal(String name, int swimDistance, int ranDistance) {
        this.name = name;
        this.swimDistance = swimDistance;
        this.ranDistance = ranDistance;
    }

    public abstract void ran (int distance);

    public abstract void swim (int distance);

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ",\n swimDistance=" + swimDistance +
                ",\n ranDistance=" + ranDistance +
                "}\n";
    }
}
