package theoryLesson.lesson15;

import java.util.Comparator;

public class CustomComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Integer key1 = (Integer) o1;    // o1：新加入的键
        Integer key2 = (Integer) o2;    // o2：树中已有的键
        return key2.compareTo(key1);    // 两个操作数对调 => 降序
        // 泛型等价写法：implements Comparator<Integer>，compare(Integer o1, Integer o2)
    }
}

