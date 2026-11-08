package theoryLesson.lesson15;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMap_Demo2 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Set keys = map.keySet();              // 所有 key 组成的 Set
        Iterator iter = keys.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Integer value = (Integer) map.get(key);   // 每个 key 再查一次
            System.out.println(key + " = " + value);
        }
    }
}
