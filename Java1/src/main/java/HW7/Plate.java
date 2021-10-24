package HW7;

public class Plate {

    private int capacity = 0;
    private int foodSupply = 0;


    public Plate(int capacity, int foodSupply) {
        this.capacity = capacity;
        this.foodSupply = foodSupply;
    }

    public boolean getFood (int count){
        if(foodSupply < count) return false;
        foodSupply -= count;
        return true;
    }

    public void replenish (int count){
        foodSupply += count;
        if (foodSupply > count) foodSupply = count;
    }

    public void replenishToFull (){
        replenish(capacity);
    }
}
