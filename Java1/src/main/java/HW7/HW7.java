package HW7;

public class HW7 {

    private static int catsCount = 0;

    public static void main(String[] args) {
        Plate plate = new Plate(50, 50);
        Cat[] cats = getCatsArray(10);
        for (Cat cat : cats) {
            cat.feeding(plate);
        }
        plate.replenishToFull();
        for (Cat cat : cats) {
            cat.feeding(plate);
        }
    }

    private static Cat[] getCatsArray (int length){
        Cat[] cats = new Cat[length];
        for (int i = 0; i < length; i++) {
            cats[i] = getRandomCat();
        }
        return cats;
    }

    private static Cat getRandomCat (){
        catsCount ++;
        String name = "CatName" + catsCount;
        return new Cat(name, (int)(Math.random() * 20));
    }
}
