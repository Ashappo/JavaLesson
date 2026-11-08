package theoryLesson.lesson15;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** Map 使用泛型：key 为 String、value 为 Integer */
public class HaspMap_GenericsDemo1 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);           // 自动装箱 int -> Integer
        map.put("two", 2);
        map.put("three", 3);

        // 方式一：keySet + Iterator<String>
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Integer value = map.get(key);       // 无需强制转换
            System.out.println(key + " = " + value);
        }

        // 方式二（推荐）：entrySet，一次取出键和值
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println("entry: " + e.getKey() + " -> " + e.getValue());
        }
    }
}
