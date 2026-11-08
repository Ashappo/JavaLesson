package theoryLesson.lesson15;
import java.util.Map;
import java.util.TreeMap;

/** 用自定义比较器把 Student 按 age 降序存入 TreeMap */
public class CustomComparatorTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Tom");  s1.setAge(19);
        Student s2 = new Student();
        s2.setName("Lucy"); s2.setAge(10);
        Student s3 = new Student();
        s3.setName("Lily"); s3.setAge(17);
        // 把比较器对象传给 TreeMap 的构造方法
        Map<Integer, Student> map = new TreeMap<>(new CustomComparator());
        map.put(s2.getAge(), s2);      // 键 10
        map.put(s3.getAge(), s3);      // 键 17
        map.put(s1.getAge(), s1);      // 键 19

        for (Map.Entry<Integer, Student> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}
