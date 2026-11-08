package theoryLesson.lesson15;

import java.util.HashMap;

public class HashMap_Expand {
    public static void main(String[] args) {
        HashMap map2 = new HashMap();

        Integer i1 = Integer.valueOf(5);
        Integer i2 = i1;                                  // 与 i1 是同一个对象
        map2.put(i1, i1);
        map2.put(i2, i2);                                 // 键相同：覆盖 value
        System.out.println("第 1 次 size = " + map2.size());

        map2.put(Integer.valueOf(5), Integer.valueOf(5));        // equals 相等：仍覆盖
        System.out.println("第 2 次 size = " + map2.size());

        map2.put(Integer.valueOf("5"), Integer.valueOf("5"));    // 字符串 "5" 转成整数 5
        System.out.println("第 3 次 size = " + map2.size());

        map2.put(Integer.valueOf('5'), Integer.valueOf('5'));    // 字符 '5' 的码值是 53，是新键
        System.out.println("第 4 次 size = " + map2.size());
        System.out.println("map2 = " + map2);
    }
}

