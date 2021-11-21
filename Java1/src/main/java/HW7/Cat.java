package HW7;

public class Cat {

    private String name;
    private int appetite;
    private boolean isWellFed = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void feeding (Plate plate){
        if (isWellFed) {
            System.out.println("Cat " + name + " well-fed");
            return;
        } else if (plate.getFood(appetite)){
            isWellFed = true;
            System.out.println("Cat " + name + " ate");
            return;
        }  else {
            System.out.println("Did not enough food");
        }
    }
}
