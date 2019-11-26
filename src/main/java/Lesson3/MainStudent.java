package Lesson3;

import java.io.*;

public class MainStudent {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Book book = new Book("Java");
        Student s = new Student(15, "Nikolay");
        s.book = book;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser"));

        oos.writeObject(s);

        oos.close();

//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud.ser"));
//        Student s2 = (Student)ois.readObject();
//        s2.info();
//        ois.close();
   }
}

// Externalizable