package Lesson7.Homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static java.util.Comparator.comparingInt;

public class MainClass {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        start(TestOne.class);
        System.out.print(System.lineSeparator());
        start(TestTwo.class);
    }

    public static void start(Class myClass) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Method[] methods = myClass.getDeclaredMethods();
        int beforeSuite = 0;
        int afterSuite = 0;
        List<Method> tests = new ArrayList<>();

        for (Method method: methods) {
            String annotation = method.getDeclaredAnnotations()[0].annotationType().getSimpleName();
            if (annotation.equals("BeforeSuite")) {
                beforeSuite++;
                if (beforeSuite > 1) throw new RuntimeException(annotation + " можно использовать только 1 раз!");
            } else if (annotation.equals("AfterSuite")) {
                afterSuite++;
                if (afterSuite > 1) throw new RuntimeException(annotation + " можно использовать только 1 раз!");
            } else if (annotation.equals("Test")) {
                tests.add(method);
            }
        }

        tests.sort(comparingInt(priority ->
                priority.getAnnotation(Test.class).priority()));

        for (Method method: methods) {
            String annotation = method.getDeclaredAnnotations()[0].annotationType().getSimpleName();
            if (annotation.equals("BeforeSuite")) {
                tests.add(0, method);
            }
            if (annotation.equals("AfterSuite")) {
                tests.add(method);
            }
        }

        for (Method method: tests) {
            method.invoke(myClass.getSimpleName());
            if (!method.getDeclaredAnnotations()[0].annotationType().getSimpleName().equals("BeforeSuite") &&
                    !method.getDeclaredAnnotations()[0].annotationType().getSimpleName().equals("AfterSuite"))
                System.out.print(" приоритет: " + method.getAnnotation(Test.class).priority() + System.lineSeparator());
        }
    }
}
