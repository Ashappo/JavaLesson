package ExperimentLesson.lesson02;

public class TestStudent {
    public static void main(String[] args) {
        System.out.println("=====调用无参构造 new Student()=====");
        Student s1 = new Student();
        s1.printStudent();

        System.out.println("\n=====调用两参构造 new Student(\"小明\",18)=====");
        Student s2 = new Student("小明",18);
        s2.printStudent();

        System.out.println("\n=====调用完整三参构造=====");
        Student s3 = new Student("小红",20,"2026001");
        s3.printStudent();
    }
}

