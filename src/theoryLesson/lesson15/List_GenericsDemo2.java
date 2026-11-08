package theoryLesson.lesson15;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Iterator 遍历带泛型的 List */
public class List_GenericsDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next();   // 直接是 String，无需强制转换
            System.out.println(s);
        }
    }
}
