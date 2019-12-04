package Lesson7.Homework;

public class TestTwo {

    @AfterSuite
    public static void taskAfter() {
        System.out.println(TestOne.class.getSimpleName() + " После исполнения теста");
    }

    @Test(priority = 7)
    public static void task1() {
        System.out.print(TestTwo.class.getSimpleName() + " task1");
    }

    @Test(priority = 5)
    public static void task2() {
        System.out.print(TestTwo.class.getSimpleName() + " task2");
    }
}