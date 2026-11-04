package theoryLesson.lesson13;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormat_Demo1 {
    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //将Date类型的日期转换成指定的字符串形式
        String s1 = sdf1.format(d);
        String s2 = sdf2.format(d);
        System.out.println(s1);
        System.out.println(s2);
        //错误示例
//        SimpleDateFormat sdf3 = new SimpleDateFormat("tttt-MM-dd HH:mm:ss");
//        String  s3 = sdf3.format(d);//模板字母不能乱写，非法字母会抛出异常
    }
}

