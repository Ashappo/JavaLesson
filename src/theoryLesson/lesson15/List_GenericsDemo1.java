package theoryLesson.lesson15;

import java.util.ArrayList;
import java.util.List;

public class List_GenericsDemo1{
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        // list.add(1);              // 放开这行：编译期报错，只能存 String
        for (String v : list) {      // 取出的直接是 String，省去强制转换
            System.out.println(v);
        }
    }
}
