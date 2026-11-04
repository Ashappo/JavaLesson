package theoryLesson.lesson14;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args){
        List list = new ArrayList(); // 多态写法
        System.out.println("初始化后，集合：" + list);

        list.add("one");
        System.out.println("添加 one 后：" + list);

        list.add("second");
        System.out.println("添加 second 后：" + list);

        list.add(4);       // 自动装箱 Integer
        System.out.println("添加 4 后：" + list);

        list.add(5.0F);    // 自动装箱 Float
        System.out.println("添加 5.0F 后：" + list);

        list.add("second");// 允许重复元素
        System.out.println("再次添加 second 后：" + list);
    }
}

