package Lesson6.Homework;
import java.util.Arrays;

public class Array {

    public static void main(String[] args) {

        int[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        System.out.println("Входной массив: " + Arrays.toString(array));
        System.out.println("Массив после оброботки: " + Arrays.toString(replaceNums(array)));

    }

    static int[] replaceNums(int[] array) {
        int[] nums = new int[2];
        int count = 1;

        StringBuilder stringArray = new StringBuilder(Arrays.toString(array));
        stringArray.reverse();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4)
                break;
            else if (i == array.length - 1)
                throw new RuntimeException();
        }

        for (int i = 0; i < stringArray.length(); i++) {
            if (stringArray.charAt(i) == '4') break;
            if ((stringArray.charAt(i) != ' ') && (stringArray.charAt(i) != ',') && (stringArray.charAt(i) != ']')) {
                nums[count] = Integer.parseInt(String.valueOf(stringArray.charAt(i)));
                count--;
            }
        }
        return nums;
    }

    static boolean OneFourNums(int[] array) {
        for (int value : array) {
            if (value == 4 || value == 1) {
                return true;
            }
        }
        return false;
    }
}
