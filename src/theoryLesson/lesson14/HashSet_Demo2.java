package theoryLesson.lesson14;

import java.util.HashSet;

public class HashSet_Demo2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(5);                          // 自动装箱 Integer.valueOf(5)
        hs.add(Integer.valueOf(5));
        hs.add(Integer.valueOf("5"));
        hs.add(Integer.valueOf('5'));      // char '5' → ASCII码53

        //思考：此时hs这个HashSet集合对象中有多少个对象？
        System.out.println(hs);
        System.out.println(hs.size());
    }
}
