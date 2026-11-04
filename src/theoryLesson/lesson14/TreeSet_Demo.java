package theoryLesson.lesson14;

import java.util.Iterator;
import java.util.TreeSet;

// StudentTree类实现Comparable接口
class StudentTree implements Comparable<StudentTree> {
    private String name;
    private int age;

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

    // 重写比较方法：核心！TreeSet排序规则
    @Override
    public int compareTo(StudentTree o) {
        // 按年龄升序
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "StudentTree{name='" + name + "', age=" + age + "}";
    }
}

public class TreeSet_Demo {
    public static void main(String[] args){
        StudentTree s1 = new StudentTree();
        s1.setName("Tom");
        s1.setAge(19);
        StudentTree s2 = new StudentTree();
        s2.setName("Lucy");
        s2.setAge(20);
        StudentTree s3 = new StudentTree();
        s3.setName("Lily");
        s3.setAge(17);

        TreeSet ts = new TreeSet();
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);

        Iterator iter = ts.iterator();
        while(iter.hasNext()) {
            StudentTree s = (StudentTree)iter.next();
            System.out.println(s);
        }
    }
}
