package TheoryLesson.lesson13;

public class StringBuffer_Demo2 {
    public StringBuffer getStringBuffer(StringBuffer sb2){
        sb2.append("11");
        return sb2;
    }
    public static void main(String[] args) {
        StringBuffer_Demo2 sd = new StringBuffer_Demo2();
        StringBuffer sbb = new StringBuffer("abc");
        System.out.println(sbb.toString());
        sd.getStringBuffer(sbb);
        System.out.println(sbb.toString());
    }
}
