package TheoryLesson.lesson13;

public class StringBuffer_Demo1 {
    public static void main(String[] args) {
        StringBuffer sb1 = new StringBuffer();
        System.out.println("sb1 length:" + sb1.length());     // 0
        System.out.println("sb1 capacity:" + sb1.capacity()); // 16

        StringBuffer sb2 = new StringBuffer("hello");
        System.out.println("sb2 length:" + sb2.length());     // 5
        System.out.println("sb2 capacity:" + sb2.capacity()); // 5+16=21
    }
}
