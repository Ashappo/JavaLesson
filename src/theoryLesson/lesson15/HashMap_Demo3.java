package theoryLesson.lesson15;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** 遍历方式二（推荐）：先取所有键值对 Map.Entry，再取 key 和 value */
public class HashMap_Demo3 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // entrySet 中每个元素都是 Map.Entry，一个对象同时含 key 和 value
        Set entrySet = map.entrySet();
        Iterator iter = entrySet.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}

