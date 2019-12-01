package Lesson6;

public class Calculator {
    public int add (int x, int y) {
        return x + y;
    }

    public int div (int x, int y) {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x/y;
    }
}
