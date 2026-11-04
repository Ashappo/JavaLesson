package TheoryLesson.lesson13;

import java.util.Date;

public class TestDeprecation {
    public static void main(String[] args){
        // 该构造方法 @Deprecated，已过时
        Date d = new Date(2026,11,4);
        // getDate() 方法 @Deprecated，已过时，IDE会显示删除线
        int dt = d.getDate();
    }
}
