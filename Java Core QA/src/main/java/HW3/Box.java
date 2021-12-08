package HW3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> collectedFruits;
    double totalWeight = 0;;

    public Box() {
        collectedFruits = new ArrayList<>();
    }

    public Box(List<T> fruits) {
        collectedFruits = new ArrayList<>();
        addFruits(fruits);
    }

    public void addFruits(List<T> fruits){
        this.collectedFruits.addAll(fruits);
        for (T fruit : fruits) {
            totalWeight += fruit.getWeight();
        }
    }

    public List<T> getAllFruits (){
        List<T> tmp = new ArrayList<>(collectedFruits);
        collectedFruits.clear();
        return tmp;
    }

    public boolean compare (Box box){
        return totalWeight == box.totalWeight;
    }

    public void pourFruitsToAnotherBox (Box<T> anotherBox){
        anotherBox.addFruits(getAllFruits());
    }

    public double getWeight() {
        return totalWeight;
    }
}
