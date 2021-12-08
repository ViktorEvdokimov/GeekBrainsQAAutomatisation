package HW2;

public class MyArraySizeException extends Exception{

    public MyArraySizeException(int expectedSize) {
        super("Size of array should be " + expectedSize + "x" + expectedSize);
    }
}
