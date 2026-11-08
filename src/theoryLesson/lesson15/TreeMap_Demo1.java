package theoryLesson.lesson15;

import java.util.Map;
import java.util.TreeMap;

/** TreeMap 自然排序：键必须实现 Comparable，本例的键是 String */
public class TreeMap_Demo1 {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("4", "Lucy");
        map.put("1", "Tom");
        map.put("2", "Lily");
        map.put("3", "Rose");

        System.out.println(map);     // 按键的字典序升序输出，与 put 的先后无关
    }
}
