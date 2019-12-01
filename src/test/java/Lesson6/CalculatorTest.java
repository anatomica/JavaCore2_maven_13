package Lesson6;
import org.junit.*;

public class CalculatorTest {

    Calculator calculator;

    @Before
    public void init() {
        System.out.println("Start Test");
        calculator = new Calculator();
    }

    @After
    public  void shutdown() {
        System.out.println("Test End");
    }

    @AfterClass
    public static void shutdownClass() {
        System.out.println("ALL tests End");
    }

    @Test
    public void test1() {
        Assert.assertEquals(5, calculator.add(2, 2));
    }

    @Test
    public void test2() {
        Assert.assertEquals(10, calculator.add(2, 8));
    }

    @Test
    public void test3() {
        Assert.assertEquals(20, calculator.add(21, -1));
    }

    @Test(expected = ArithmeticException.class, timeout = 100)
    @Ignore("Не хочу ждать 100 секунд")

    public void test4() {
        Calculator calculator = new Calculator();

        Assert.assertEquals(2, calculator.div(2, 0));
    }

}