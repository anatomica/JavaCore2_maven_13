package Lesson7.Anon;

@XTable(name = "students")
public class Student {
    @XField
    int id;

    @XField
    String name;

    @XField
    int age;

    @XField
    String al;


    public Student(int id, String name, int age, String al) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.al = al;
    }
}
