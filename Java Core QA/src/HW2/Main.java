package HW2;

import java.util.Set;

public class Main {

    private static final int EXPECTED_ARRAY_SIZE = 4;

    public static void main(String[] args) {
        String[][] array = new String[][]{
                {"5",  "7",  "4",  "6"},
                {"0",  "-7", "12", "5"},
                {"3",  "2",  "1",  "-15"},
                {"-3", "1",  "7",  "6"},
        };
        try {
            System.out.println(transformAndGetSumFromArray(array));
//            array[3][1] = "someString";
//            System.out.println(transformAndGetSumFromArray(array));
//            array = new String[4][2];
//            System.out.println(transformAndGetSumFromArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }
    }

    private static int transformAndGetSumFromArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != EXPECTED_ARRAY_SIZE) {
            throw new MyArraySizeException(EXPECTED_ARRAY_SIZE);
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != EXPECTED_ARRAY_SIZE) {
                throw new MyArraySizeException(EXPECTED_ARRAY_SIZE);
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
