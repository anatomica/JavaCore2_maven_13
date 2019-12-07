package Lesson8.Homework;

public class MainClass {

    private final static int LINE = 7;
    private final static int COLUMN = 10;

    public static void main(String[] args) {
        int[][] array = new int[LINE][COLUMN];
        Spiral(array);
    }

    private static void Spiral (int[][] array) {
        int number = 1;
        int stopBefore = 0;
        int degrease1 = 1;
        int degrease2 = 2;

        try {
            excitable:
            for (int i = 0; i < COLUMN; i++) {
                for (int j = stopBefore; j < COLUMN - stopBefore; j++) {
                    if (j != COLUMN - degrease1) {
                        if (array[i][j] != 0) break excitable;
                        array[i][j] = number;
                        number++;
                    }
                    if (j == COLUMN - degrease1) {
                        for (int k = stopBefore; k < LINE - stopBefore; k++) {
                            if (array[k][j] != 0) break excitable;
                            array[k][j] = number;
                            number++;
                        }
                        for (int k = (COLUMN - degrease2); k >= stopBefore; k--) {
                            if (array[LINE - degrease1][k] != 0) break excitable;
                            array[LINE - degrease1][k] = number;
                            number++;
                        }
                        stopBefore++;
                        for (int k = (LINE - degrease2); k >= stopBefore; k--) {
                            if (array[k][i] != 0) break excitable;
                            array[k][i] = number;
                            number++;
                        }
                        degrease1++;
                        degrease2++;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        PrintArray.printArr(array);
    }

}
