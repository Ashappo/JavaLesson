package ExperimentLesson.lesson02;

public class TestRelation {
    public static void main(String[] args) {
        //学生对象独立创建（聚合特点：部分可以独立于整体）
        Student s1 = new Student("张三",19,"20260101");
        Student s2 = new Student("李四",18,"20260102");

        //创建班级对象
        Clazz clazz = new Clazz("Java一班",10);
        //把学生加入班级
        clazz.addStudent(s1);
        clazz.addStudent(s2);

        clazz.showClassInfo();

        //聚合演示：班级引用置null，学生对象还可以访问
        System.out.println("\n聚合演示：班级引用置null，学生对象还可以访问");
        clazz = null;
        s1.printStudent();
    }
}

