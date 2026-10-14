package TheoryLesson.lesson08;

public class EqualsDemo2 {

    public static void main(String[] args) {

        Student2 s1 = new Student2();
        s1.name = "张三";
        s1.age = 20;

        Student2 s2 = new Student2();
        s2.name = "张三";
        s2.age = 20;

        System.out.println(s1.equals(s2)); // true
    }
}

class Student2 {
    String name;
    int age;

    @Override
    public boolean equals(Object obj) {
        Student2 s = (Student2) obj;

        return this.age == s.age
                && this.name.equals(s.name);
    }
}