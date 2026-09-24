package TheoryLesson.lesson07.example2;

public class Person {
    private String name;
    private int age;
    private String sex;

    public Person(){
        System.out.println("构造器Person()被调用");
        this.sex = "Male";
        System.out.println("name=" + name + " ,age=" + age + " ,sex=" + sex);
    }
    public Person(String theName){
        this();
        System.out.println("构造器Person(String theName)被调用");
        this.name = theName;
        System.out.println("name="+name+" ,age="+age+" ,sex="+sex);
    }
    public Person(String theName,int theAge){
        System.out.println("构造器Person(String theName,int theAge)被调用");
        this.name = theName;
        this.age = theAge;
        System.out.println("name="+name+" ,age="+age+" ,sex="+sex);
    }
    //初始化块
    {
        this.name = "Tony";
        this.age = 50;
        this.sex = "Female";
        System.out.println("Person初始化块执行后：name=" + name + " ,age=" + age + " ,sex=" + sex);
    }
}
