package Lesson7.main;

import Lesson7.Anon.MyAnon;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {


        Class c = Cat.class;

/*        Field[] fields = c.getDeclaredFields();

        for(Field o: fields) {
            System.out.println(o);
        }*/

/*        Method[] methods = c.getDeclaredMethods();
        for(Method o: methods) {
            System.out.println(o);
        }

        Cat cat = new Cat("Barsik", "black", 1);
        Field f = c.getDeclaredField("age");
        int mods = f.getModifiers();
        System.out.println(mods);
        f.setAccessible(true);
        f.set(cat, 5);
        cat.info();

        Class cl = URLClassLoader.newInstance(new URL[]{new File("X:/Lessons/java3").toURL()}).loadClass("Human");
        Constructor constructor = cl.getConstructor(String.class);
        Object human = constructor.newInstance("Martin");
        Method m = cl.getDeclaredMethod("info");
        f.setAccessible(true);
        m.invoke(human);*/

        Cat cat = new Cat("Vasya", "green", 2);

        Method[] methods = c.getDeclaredMethods();

        for (Method o: methods) {
            if(o.isAnnotationPresent(MyAnon.class)){
                System.out.println(o.getAnnotation(MyAnon.class).priority());
                o.invoke(cat);

            }
        }
    }
}


class Cat {
    String name;
    public String color;
    private int age;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public void info() {
        System.out.println(this.name + this.age);
    }

    @MyAnon(priority = 2)
    public void info1() {
        System.out.println("Вывод текста из метода инфо один");
    }

    @MyAnon()
    public void info2() {
        System.out.println("Вывод текста из метода инфо два");
    }

    @MyAnon(priority = 1)
    public void info3() {
        System.out.println("Вывод текста из метода инфо три");
    }
}