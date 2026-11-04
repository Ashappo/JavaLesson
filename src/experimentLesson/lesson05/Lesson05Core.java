package experimentLesson.lesson05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 实验五 · 集合的应用（List 与 Set）—— 课堂单文件版。
 *
 *
 * <pre>
 * javac -encoding UTF-8 -d out experimentLesson/lesson05/Lesson05Core.java
 * java  -cp out ExperimentLesson.lesson05.Lesson05Core
 * </pre>
 *
 * <p><b>：</b></p>
 * <pre>
 * ① List 增删改查            3 分钟   →  add/get/set/remove/size + remove 的坑
 * ② 四种遍历                 4 分钟   →  写法不同，结果一样，按场景选
 * ③ 遍历中删除               8 分钟   →  本实验最重要的一段，现场跑出异常
 * ④ HashSet 去重             9 分钟   →  三个对照实验讲透 hashCode 与 equals
 * ⑤ TreeSet 排序             9 分钟   →  Comparable / Comparator + 判重陷阱
 * ⑥ 四句话收尾               2 分钟
 * </pre>
 *
 *
 * @author 面向对象程序设计课程组
 * @version 3.0
 */
public class Lesson05Core {

    /**
     * 程序入口：按顺序跑完六段。
     *
     * @param args 未使用
     */
    public static void main(String[] args) {
        section1List();
        section2Traverse();
        section3RemoveInLoop();
        section4HashSet();
        section5TreeSet();
        section6Summary();
    }

    // ==================== ①===================

    /** List 的增删改查：只讲五个方法 + 一个坑。 */
    private static void section1List() {
        title("① List：增删改查");

        List<Student> list = new ArrayList<>();
        list.add(new Student(1001, "张三", 88.5));
        list.add(new Student(1002, "李四", 92.0));
        list.add(new Student(1003, "王五", 76.5));
        System.out.println("add ×3        → " + list);
        System.out.println("get(0) / size → " + list.get(0) + " / " + list.size());

        list.set(1, new Student(1002, "李四", 95.0));
        list.remove(0);
        System.out.println("set + remove  → " + list);

        // 唯一要记的坑：实参是 int 时，删的是下标
        List<Integer> nums = new ArrayList<>(Arrays.asList(10, 20, 30));
        nums.remove(1);
        System.out.println("nums.remove(1) → " + nums + "   删下标！按值删要写 remove(Integer.valueOf(20))");

        // LinkedList 只记两个方法
        LinkedList<Student> linked = new LinkedList<>();
        linked.add(new Student(1001, "张三", 88.5));
        linked.addFirst(new Student(1002, "李四", 92.0));
        System.out.println("addFirst       → " + linked + "   插到最前面");
        linked.removeFirst();
        System.out.println("removeFirst    → " + linked + "   删掉第一个");
    }

    // ==================== ②=================

    /** 四种遍历："写法不同、结果一样"。 */
    private static void section2Traverse() {
        title("② 四种遍历：写法不同，结果完全一样");

        List<Student> list = sample();

        System.out.print("1 普通 for  ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + " ");
        }
        System.out.println();

        System.out.print("2 Iterator  ");
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().name + " ");
        }
        System.out.println();

        System.out.print("3 增强 for  ");
        for (Student s : list) {
            System.out.print(s.name + " ");
        }
        System.out.println();

        System.out.print("4 forEach   ");
        list.forEach(s -> System.out.print(s.name + " "));
        System.out.println();

        System.out.println("→ 要下标用 1；要边遍历边删用 2；只读求简洁用 3 或 4。");
    }

    // ==================== ③ （重点） ====================

    /** 遍历中删除：先跑错误写法看异常，再给正确写法。 */
    private static void section3RemoveInLoop() {
        title("③ 遍历中删除：全实验最重要的一段");

        // 错误写法：增强 for 里直接 remove
        List<String> wrong = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        try {
            for (String s : wrong) {
                if ("B".equals(s)) {
                    wrong.remove(s);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("错误写法：增强 for 里 remove → ConcurrentModificationException");
        }

        // 正确写法一：Iterator.remove()
        List<String> right = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Iterator<String> it = right.iterator();
        while (it.hasNext()) {
            if ("B".equals(it.next())) {
                it.remove();
            }
        }
        System.out.println("正确写法：Iterator.remove()   → " + right);

        // 正确写法二：JDK 8 的 removeIf（内部就是 Iterator）
        List<Student> list = sample();
        list.removeIf(s -> s.score < 80);
        System.out.println("更简洁：removeIf(成绩<80)     → " + list);

        System.out.println("→ 一句话：遍历删除只能用 Iterator.remove() 或 removeIf()。");
    }

    // ==================== ④ 9 分钟（重点） ====================

    /** HashSet 去重：三个对照实验，不背原理看数据。 */
    private static void section4HashSet() {
        title("④ HashSet 为什么能去重");

        Set<Plain> plain = new HashSet<>();
        plain.add(new Plain("K"));
        plain.add(new Plain("K"));

        Set<OnlyEquals> onlyEquals = new HashSet<>();
        onlyEquals.add(new OnlyEquals("K"));
        onlyEquals.add(new OnlyEquals("K"));

        Set<Student> students = new HashSet<>();
        students.add(new Student(1001, "张三", 88.5));
        students.add(new Student(1001, "张三", 88.5));

        System.out.println("两者都不重写      size = " + plain.size() + "   内容相同的两个对象都留下了");
        System.out.println("只重写 equals     size = " + onlyEquals.size() + "   hashCode 不同 → 落在不同桶 → 去重失败");
        System.out.println("equals+hashCode   size = " + students.size() + "   只有这样才能去重");

        boolean repeat = students.add(new Student(1001, "张三", 88.5));
        System.out.println("add 重复元素返回  " + repeat + "  （Set 与 List 最直观的区别）");
        System.out.println("→ hashCode 决定「放哪个桶」，equals 决定「桶里有没有它」，少一个就失效。");
    }

    // ==================== ⑤ （重点） ====================

    /** TreeSet：自动排序 + 两套排序规则 + 判重陷阱。 */
    private static void section5TreeSet() {
        title("⑤ TreeSet：自动排序与两套排序规则");

        Set<Integer> nums = new TreeSet<>();
        nums.addAll(Arrays.asList(50, 20, 80, 10, 60, 20));
        System.out.println("乱序添加 50,20,80,10,60,20 → " + nums);

        // 自然排序：Student 实现了 Comparable，按学号
        Set<Student> byId = new TreeSet<>();
        byId.addAll(sample());
        System.out.println("Comparable 按学号   → " + byId);

        // 定制排序：构造时传入 Comparator
        Set<Student> byScore = new TreeSet<>((Student a, Student b) -> Double.compare(b.score, a.score));
        byScore.addAll(sample());
        System.out.println("Comparator 成绩降序 → " + byScore);

        // 陷阱：比较器只写一个关键字，同分的人被当成重复元素丢掉
        Set<Student> bad = new TreeSet<>((Student a, Student b) -> Double.compare(b.score, a.score));
        bad.add(new Student(1001, "张三", 88.5));
        bad.add(new Student(1003, "王五", 88.5));
        bad.add(new Student(1004, "赵六", 88.5));
        System.out.println("三人同分 88.5，只比成绩 → 只剩 " + bad.size() + " 人（应为 3）");

        System.out.println("→ TreeSet 判重看比较结果是否返回 0，不看 equals；比较器要把所有关键字写全。");
    }

    // ==================== ⑥  ====================

    /** 四句话收尾。 */
    private static void section6Summary() {
        title("⑥ 记住四句话");

        System.out.println("1. List 有序、可重复、有下标；Set 不可重复（HashSet 无序，TreeSet 有序）。");
        System.out.println("2. 遍历删除只能用 Iterator.remove() 或 removeIf()。");
        System.out.println("3. HashSet 去重 = hashCode 分桶 + equals 比对。");
        System.out.println("4. TreeSet 排序靠 Comparable（自然）/ Comparator（定制），判重看比较结果是否为 0。");
    }

    // ==================== 辅助方法 ====================

    /** 打印分段标题 */
    private static void title(String text) {
        System.out.println();
        System.out.println("==== " + text + " ====");
    }

    /** 课堂固定演示数据：四人成绩 */
    private static List<Student> sample() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1001, "张三", 88.5));
        list.add(new Student(1002, "李四", 92.0));
        list.add(new Student(1003, "王五", 76.5));
        list.add(new Student(1004, "赵六", 59.0));
        return list;
    }

    // ==================== 演示用内部类 ====================

    /** 标准集合元素：按学号重写 equals / hashCode，并实现 Comparable（按学号比较）。 */
    static class Student implements Comparable<Student> {

        final int id;
        final String name;
        final double score;

        Student(int id, String name, double score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || (o != null && getClass() == o.getClass() && id == ((Student) o).id);
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(id, o.id);
        }

        @Override
        public String toString() {
            return name + "(" + id + "," + score + ")";
        }
    }

    /** 对照实验 A：什么都不重写。 */
    static class Plain {

        private final String v;

        Plain(String v) {
            this.v = v;
        }
    }

    /** 对照实验 B：只重写 equals，故意不重写 hashCode。 */
    static class OnlyEquals {

        private final String v;

        OnlyEquals(String v) {
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || (o != null && getClass() == o.getClass()
                    && v.equals(((OnlyEquals) o).v));
        }
    }
}
