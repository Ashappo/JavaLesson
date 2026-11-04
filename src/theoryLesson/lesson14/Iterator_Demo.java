package theoryLesson.lesson14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterator_Demo {
    public static void main(String[] args) {
        List data = new ArrayList();
        data.add("Lucy");
        data.add("Lili");
        data.add("Tom");
        System.out.println("删除前集合：" + data);

        Iterator iter = data.iterator();
        while(iter.hasNext()){
            String s = (String) iter.next();
            // 如果元素是 Lili，就删除
            if("Lucy".equals(s)){
//                data.remove(s); // ❌ 集合自己的remove，触发并发修改异常
                iter.remove(); // ✅ 迭代器的remove，安全
            }
            System.out.println("取出元素：" + s);
        }
        System.out.println("删除后集合：" + data);
    }
}

