package TheoryLesson.lesson08.Interface;
//示例2－－使用接口完成

interface Person{
    //interface的属性一般用于表示常量，我们这里用不到  所以没用定义
    //定义方法：睡觉
    public void sleeping();
    //定义方法：吃饭
    public void eating();
    //定义方法：说话
    public void talking();
}

class Teacher implements Person{

    //实现接口中的方法
    public void sleeping(){
        System.out.println("Teacher在睡觉");
    }

    //实现接口中的方法
    public void eating(){
        System.out.println("Teacher可以eating()");
    }

    //实现接口中的方法
    public void talking(){
        System.out.println("Teacher可以talking()");
    }

    //新增老师的方法
    public void teaching(){
        System.out.println("Teacher可以teaching()");
    }
}

class Student implements Person{

    //实现接口中的方法
    public void sleeping(){
        System.out.println("Student在睡觉");
    }

    //实现接口中的方法
    public void eating(){
        System.out.println("Student可以eating()");
    }

    //实现接口中的方法
    public void talking(){
        System.out.println("Student可以talking()");
    }

    //新增学生的方法
    public void study(){
        System.out.println("Student可以study()");
    }

}

public class Test{

    public static void main(String[] args){
//        - 知识点 1：对象类型，知识点 2：对象造型
//        - 实例化一个Person变量  实际对象是Student，编译看左边，运行看右边。
//        - 对象类型：Person 是编译类型（包含静态/引用类型）决定能直接调用哪些方法：只能调用 Person 里面定义的方法，编译阶段javac看此类型，编译类型不会改变。
//        - 右侧为运行类型new Student()→ 运行类型：Student，在运行时 JVM 看这个类型，编译类型可变。
//        - p是引用变量，在栈内存；
//        - new Student() 创建的对象，放在堆内存；
//        - p里面存的是堆里面 Student 对象的内存地址；
//        - p是 Person 类型 → Person 属于引用类型。
//        - 基本类型（byte,short,int,long,float,double,char,boolean）：栈内存存【真实数值】，没有 new，没有对象，不能是 null
//        - 引用类型（类class、接口interface、数组[]如int[]、String[]等、枚举enum、注解Annotation：@+注解名如@interface、@Override等）：栈内存存【对象地址】，对象在堆，可以为 null，用 new 创建对象
//        ---------------------
//        - 对象造型 ：
//        1.向上转换（自动转型，隐式造型）子类引用 → 父类引用 / 接口引用，自动完成，不用强转
//        Person p = new Student();限制：只能调用编译类型（Person）拥有的方法，子类独有方法看不见
//        2.向下转型（强制造型，显式转换）父类 / 接口引用 → 子类引用，必须手动写 `(子类类型)` 强制造型
//        Person p = new Student();
//        Student s = (Student) p; //向下造型
//        s.study(); //现在可以调用Student独有的study()
//        如果p真实对象就是 Student：转型成功、如果p实际是 Teacher 对象：运行抛出ClassCastException类型转换异常
//        Person p = new Student();
//        if(p instanceof Student){
//            Student s = (Student)p;
//            s.study();
//        }
        Person p = new Student();
        p.sleeping();
        p.eating();
        p.talking();
//        Student s = (Student)p;
//        s.study();
	    /*如果想要使用学生自己的方法，则必须进行转型
		Student s = (Student)p;
		s.study();
		*/

    }
}
