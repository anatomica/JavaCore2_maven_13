package Lesson6;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import java.util.*;

@RunWith(Parameterized.class)
public class CalculatorTestParam {
    private int a;
    private int b;
    private int c;
    Calculator calculator;

    public CalculatorTestParam(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, 0},
                {1, 1, 2},
                {3, 2, 4},
                {1, -1, 0},
                {1.0, 2, 3},
        });
    }

    @Before
    public void init() {
        System.out.println("Start Test");
        calculator = new Calculator();
    }

    @Test
    public void add() {
        Assert.assertEquals(c, calculator.add(a, b));

    }
}