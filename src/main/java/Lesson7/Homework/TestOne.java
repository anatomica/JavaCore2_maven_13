package Lesson7.Homework;

public class TestOne {

    @BeforeSuite
    public static void taskBefore() {
        System.out.println(TestOne.class.getSimpleName() + " До исполнения теста");
    }

    @AfterSuite
    public static void taskAfter() {
        System.out.println(TestOne.class.getSimpleName() + " После исполнения теста");
    }

    @Test(priority = 5)
    public static void task1() {
        System.out.print(TestOne.class.getSimpleName() + " task1");
    }

    @Test()
    public static void task2() {
        System.out.print(TestOne.class.getSimpleName() + " task2");
    }

    @Test(priority = 1)
    public static void task3() {
        System.out.print(TestOne.class.getSimpleName() + " task3");
    }

    @Test()
    public static void task4() {
        System.out.print(TestOne.class.getSimpleName() + " task4");
    }

}