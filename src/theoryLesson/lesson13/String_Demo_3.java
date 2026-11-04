package theoryLesson.lesson13;

public class String_Demo_3 {
    public static void main(String[] args) {
        String name1 = "luuy6";
        String name2 = name1.concat("-new");
        String name3 = name1.replace('u', 'o');
        String name4 = name1.substring(1);
        String name5 = name1.substring(1,5);
        System.out.println("name1: "  + name1);
        System.out.println("name2: "  + name2);
        System.out.println("name3: "  + name3);
        System.out.println("name4: "  + name4);
        System.out.println("name5: "  + name5);
    }
}

