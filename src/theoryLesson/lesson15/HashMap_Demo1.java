package theoryLesson.lesson15;

import java.util.HashMap;

public class HashMap_Demo1 {
    public static void main(String[] args) {
        HashMap map = new HashMap();          // 课件写法：原始类型（未加泛型）
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("map = " + map);
        System.out.println("size = " + map.size());
        System.out.println("get(\"one\") = " + map.get("one"));
        System.out.println("containsKey(\"two\") = " + map.containsKey("two"));

        // key 重复时不再新增元素，而是用新 value 覆盖旧 value，并返回旧 value
        Object old = map.put("one", 111);
        System.out.println("put 返回旧值 = " + old + "，覆盖后 size = " + map.size());
    }
}
