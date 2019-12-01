package Lesson6.Homework;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayTest {

    private int[] array;
    private int[] result;

    public ArrayTest(int[] array, int[] result) {
        this.array = array;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int[]{4, 1, 7}, new int[]{1, 7}},
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{4, 1, 7}},
                {new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1}, new int[]{1, 7}},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 7}},
                {new int[]{}, new int[]{1, 7}},
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
    public void arrayCheck() {
        Assert.assertArrayEquals(Array.replaceNums(array), result);
    }

}