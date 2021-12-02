package HW2;

public class MyArrayDataException extends Exception{

    public MyArrayDataException(int row, int col) {
        super("Array should contains only numbers, but in " + row + " " + col + " not number");
    }
}
