package TheoryLesson.lesson13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormat_Demo2 {
    public static void main(String[] args) {
        SimpleDateFormat sdf1  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf2  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            //将字符串格式的日期转换成Date类型
            d1 = sdf1.parse("2026/11/4 08:56:22");
            d2 = sdf2.parse("2026-11-14 08:56:22");
            System.out.println(d1);
            System.out.println(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

