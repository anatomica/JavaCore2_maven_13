package Lesson6.Homework;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NumsArrayTest {

    private int[] array;
    // private boolean result;

    public NumsArrayTest(int[] array) {
        this.array = array;
        // this.result = result;
    }

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[]{1, 4, 1, 4}},
                        {new int[]{4, 4, 4, 4}},
                        {new int[]{3, 3, 3, 1}},
                        {new int[]{5, 5, 5, 5}},
                        {new int[]{}}
                }
        );
    }

    @Before
    public void init() {
        System.out.println("Start Test");
    }

    @After
    public void tearDown() throws Exception {
        array = null;
    }
    @After
    public  void shutdown() {
        System.out.println("Test End");
    }

    @Test
    public void checkNums() {
        Assert.assertTrue(Array.OneFourNums(array));
        System.out.println(Array.OneFourNums(array));
    }

}