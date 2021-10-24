package HW6;

public class HW6 {
    public static void main(String[] args) {

        Animal[] animals = new Animal[20];
        AnimalCounter animalCounter = new AnimalCounter();
        for (int i = 0; i < animals.length; i++) {
            if (Math.random() > 0.5f) animals[i] = animalCounter.getDog();
            else animals[i] = animalCounter.getCat();
            System.out.println(animals[i]);
            animals[i].run(200);
            animals[i].run(500);
            animals[i].swim(10);
            System.out.println();
        }
        System.out.println(animalCounter);
    }
}
