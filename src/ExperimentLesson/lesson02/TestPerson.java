package ExperimentLesson.lesson02;

/**
 * 测试类：创建对象、域默认值、引用赋值、null
 */
public class TestPerson {
    public static void main(String[] args) {
        //1. 使用无参构造创建对象，观察域默认初始值
        Person p1 = new Person();
        p1.showInfo();
        /*
        域默认值：
        String引用类型默认null
        int默认0
        boolean默认false
         */

        // public属性可以直接赋值访问
        p1.remark = "我是p1";
        // 私有属性不能直接写 p1.name="张三"; 编译报错
        p1.setName("张三");
        p1.setAge(20);
        p1.showInfo();
        System.out.println("是否成年：" + p1.isAdult());

        //2. 使用带参构造器创建第二个对象
        Person p2 = new Person("李四",16);
        p2.remark = "我是p2";
        p2.showInfo();
        System.out.println("是否成年：" + p2.isAdult());

        //3. 对象引用赋值，p3和p2指向同一个对象，不是复制对象！
        Person p3 = p2;
        System.out.println("\n---引用赋值测试 p3=p2---");
        System.out.println("修改p3的age，p2也会跟着变");
        p3.setAge(22);
        p2.showInfo();

        //4. 判断引用是否相等 == 判断是否同一个对象
        System.out.println("\n==引用比较：");
        System.out.println(p2 == p3); // true，同一引用
        System.out.println(p1 == p2); // false，不同对象

        //5. null的使用：引用不指向任何对象
        Person p4 = null;
        System.out.println("\np4 = null");
        // p4.showInfo(); //运行报错：NullPointerException空指针异常
    }
}

