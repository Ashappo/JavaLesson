package theoryLesson.lesson14;

import java.util.ArrayList;
import java.util.List;

// 学生类
class Student {
    private String name;

    // 带参构造，给学生设置名字
    public Student(String name) {
        this.name = name;
    }

    public void study() {
        System.out.println(name + " 正在学习");
    }
}

public class ForEachListDemo {
    public static void main(String[] args) {
        Student s1 = new Student("小明");
        Student s2 = new Student("小红");
        Student s3 = new Student("小刚");

        List al = new ArrayList();
        al.add(s1);
        al.add(s2);
        al.add(s3);

        // 增强for循环遍历
        for (Object o : al) {
            // 强制类型转换
            Student s = (Student) o;
            s.study();
        }
    }
}

