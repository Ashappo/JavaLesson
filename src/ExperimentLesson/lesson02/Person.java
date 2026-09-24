package ExperimentLesson.lesson02;

/**
 * 自定义Person类，练习封装、this、getter/setter
 */
public class Person {
    // 1. public域：可以外部直接访问
    public String remark;

    // 2. private私有域，外部不能直接访问，必须通过方法
    private String name;
    private int age;
    private boolean isAdult;  // 是否成年，boolean类型

    // 【无参构造器】
    public Person() {
        System.out.println("执行无参构造器");
    }

    // 【带参构造器】
    public Person(String name, int age) {
        // this：代表当前正在创建的对象，区分成员变量和局部变量
        this.name = name;
        this.age = age;
        // 根据年龄设置是否成年
        this.isAdult = age >= 18;
    }

    // ========= getter / setter 方法 =========
    // name的get set
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
        // 修改年龄同步更新是否成年
        this.isAdult = age >= 18;
    }

    // boolean类型get方法，约定以is开头，不要用get
    public boolean isAdult() {
        return this.isAdult;
    }

    // 自定义普通方法：打印对象全部信息
    public void showInfo() {
        System.out.println("-----Person信息-----");
        System.out.println("remark(public属性):" + this.remark);
        System.out.println("姓名name:" + this.name);
        System.out.println("年龄age:" + this.age);
        System.out.println("是否成年isAdult:" + this.isAdult);
    }
}
