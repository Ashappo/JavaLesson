package theoryLesson.lesson13;

public class String_Demo_2 {
    public static void main(String[] args) {
        // 字面量赋值：在常量池创建"lucy"，name1指向池对象
        String name1 = "lucy";
        // 复用常量池已有的"lucy"，和name1地址相同
        String name2 = "lucy";
        // new String：堆创建新对象；同时字面量"Lucy"往常量池存入
        String name3 = new String("Lucy");
        // 再次new，堆上新建另一个独立对象，常量池不再新建
        String name4 = new String("Lucy");
        // 字面量赋值，直接指向常量池已经存在的"Lucy"
        String name5 = "Lucy";
        System.out.println("name1 == name2: " + (name1 == name2));
        System.out.println("name3 == name4: " + (name3 == name4));
        System.out.println("name3 == name5: " + (name3 == name5));
        System.out.println("-------------");
        System.out.println("name1.equals(name2): " + name1.equals(name2));
        System.out.println("name3.equals(name4): " + name3.equals(name4));
        System.out.println("name3.equals(name5): " + name3.equals(name5));
        System.out.println("name1.equals(name3): " + name1.equals(name3));
    }
}
