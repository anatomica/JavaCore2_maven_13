package Lesson7;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        System.out.println(new Test().findNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 9, 10}));
        ;
    }

    public int findNumber(int[] arr) {

        int arrCount = arr.length;

        if (arrCount <= 3) {
            if (arr[0]+1 != arr[1] ) return ++arr[0];
            return ++arr[1];
        }

        int medium = (int) arrCount / 2;

        int result = 0;
        int[] arrLeft = new int[medium];
        int[] arrRight = new int[arrCount - medium];
        System.arraycopy(arr, 0, arrLeft, 0, medium);
        System.arraycopy(arr, medium, arrRight, 0, arrCount - medium);

        if (!checkBalance(arrLeft)) return  findNumber(arrLeft);
        if (!checkBalance(arrRight)) return findNumber(arrRight);
        return findNumber(new int[]{arr[medium - 1], arr[medium], arr[medium + 1]});

    }

    protected boolean checkBalance(int[] arr) {

        int arrCount = arr.length;
        int first = arr[0];
        int last = arr[arrCount - 1];

        if ((last - first + 1) == (arrCount))
            return true;

        return false;
    }
}
