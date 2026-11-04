package theoryLesson.lesson14;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSet_Demo1 {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("one");
        set.add("one");  //加入重复元素
        set.add("two");
        //Set集合在加入对象时，判断的是对象的内容是否一样，而不是对象的地址
        set.add(new String("one"));
        //HashSet的size()方法用于返回该HashSet集合对象的长度
        System.out.println("set的长度为：" + set.size());
        //Set 没有下标，不能用普通 for 循环（带索引）遍历；但可以用迭代器、增强 for 循环遍历
        Iterator iter = set.iterator();
        while(iter.hasNext()){
            String s = (String)iter.next();
            System.out.println(s);
        }
    }
}
