package Lesson3;

import java.io.Serializable;

public class Student extends Human implements Serializable {

    int id;
    String name;
    transient Book book;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public void info () {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
    }
}
