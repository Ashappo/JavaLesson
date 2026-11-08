package theoryLesson.lesson15;

public class Student {
    private String name;
    private int age;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return this.age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "【个人信息】-> 姓名: " + this.name + "，年龄：" + this.age;
    }
}

