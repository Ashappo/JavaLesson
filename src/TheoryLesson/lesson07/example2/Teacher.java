package TheoryLesson.lesson07.example2;

public class Teacher extends Person {
    //部门
    private String department;
    //教龄
    private int schoolAge;

    public Teacher(){
        System.out.println("构造器Teacher()被调用");
    }

    public Teacher(String name){
        //调用父类中的构造器Person(String theName)
        super(name);
        System.out.println("构造器Teacher(String name)被调用");
    }

    public Teacher(int theSchoolAge){
        schoolAge = theSchoolAge;
    }

    public Teacher(String dept,int theSchoolAge){
        //调用本类中重载的构造器Teacher(int theSchoolAge)
        this(theSchoolAge);
        department = dept;
    }
    //初始化块
    {
        this.department = "教务处";
        System.out.println("Teacher初始化块执行……");
    }
}
