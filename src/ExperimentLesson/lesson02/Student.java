package ExperimentLesson.lesson02;

public class Student {
    private String name;
    private int age;
    private String studentId;

    //①无参构造器
    public Student() {
        // this()调用本类的带3个参数构造器，复用代码
        this("未知",0,"未分配学号");
        System.out.println("无参构造执行完毕");
    }

    //②两个参数构造器
    public Student(String name, int age){
        // 调用3参构造，缺省学号
        this(name,age,"临时学号");
        System.out.println("两参数构造执行完毕");
    }

    //③三个参数完整构造器
    public Student(String name, int age, String studentId){
        System.out.println("三参完整构造器执行");
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    //getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void printStudent(){
        System.out.println("学生："+name+" 年龄："+age+" 学号："+studentId);
    }
}
