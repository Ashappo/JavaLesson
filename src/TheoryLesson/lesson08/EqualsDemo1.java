package TheoryLesson.lesson08;

//未重写 equals()
public class EqualsDemo1 {

    public static void main(String[] args) {

        Student s1 = new Student();
        s1.name = "张三";
        s1.age = 20;

        Student s2 = new Student();
        s2.name = "张三";
        s2.age = 20;

        System.out.println(s1.equals(s2)); // false
    }
}

class Student {
    String name;
    int age;
}
