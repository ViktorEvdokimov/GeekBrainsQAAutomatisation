package HW6;

public class AnimalCounter {

    private int animalCounter = 0;
    private int dogCounter = 0;
    private int catCounter = 0;

    public Dog getDog (String name, int swimDistance, int ranDistance){
        animalCounter++;
        dogCounter++;
        return new Dog(name, swimDistance, ranDistance);
    }

    public Dog getDog(){
        String name = "DogNumber" + (dogCounter + 1);
        return getDog(name, getRandomNumber(2, 20), getRandomNumber(300, 800));
    }

    public Cat getCat (String name, int swimDistance, int ranDistance){
        animalCounter++;
        catCounter++;
        return new Cat(name, swimDistance, ranDistance);
    }

    public Cat getCat(){
        String name = "CatNumber" + (catCounter + 1);
        return getCat(name, 0, getRandomNumber(100, 300));
    }

    private int getRandomNumber (int minValue, int maxValue){
        return (int)(Math.random() * (maxValue - minValue) + minValue);
    }

    public int getAnimalCounter() {
        return animalCounter;
    }

    public int getDogCounter() {
        return dogCounter;
    }

    public int getCatCounter() {
        return catCounter;
    }

    @Override
    public String toString() {
        return "AnimalCounter{\n" +
                "animals count = " + animalCounter +
                ", \ndogs count = " + dogCounter +
                ", \ncats count = " + catCounter +
                "}\n";
    }
}
