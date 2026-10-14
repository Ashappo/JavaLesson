package TheoryLesson.lesson09.Inheritance;
//示例1－－使用类的继承完成

class Person{
    //定义属性
    private int age;
    private String name;

    //定义方法
    public void sleeping(){
        System.out.println("Person在睡觉");
    }

}
class Teacher extends Person{

    //覆盖父类中的方法
    public void sleeping(){
        System.out.println("Teacher在睡觉");
    }

    //新增一个方法
    public void teaching(){
        System.out.println("Teacher可以教书");
    }

}

class Student extends Person{

    //覆盖父类中的方法
    public void sleeping(){
        System.out.println("Student在睡觉");
    }

    //新增一个方法
    public void study(){
        System.out.println("Student可以学习");
    }

}

public class Test{
    //定义一个方法,该方法可以接受Person类型的变量
    public void methodOne(Person p){
        p.sleeping();

    }

    public static void main(String[] args){
        Test test = new Test();
        Person p = new Person();
        Person s = new Student();
        Person t = new Teacher();
        test.methodOne(p);
        test.methodOne(s);
        test.methodOne(t);
    }
}
