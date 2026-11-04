package theoryLesson.lesson13;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatter_Demo2 {
    public static void main(String[] args) {
        // 方式1：内置常量 ISO_DATE_TIME 解析（稳定）
        String str1 = "2026-11-04T23:40:15.456789";
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime time1 = LocalDateTime.parse(str1, dtf1);
        System.out.println("方式1解析结果：" + time1);

        // 方式2：FormatStyle 只用来【格式化输出】，不做解析！
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String out2 = dtf2.format(now);
        System.out.println("方式2格式化输出：" + out2);

        // 方式3：ofPattern自定义模板（格式化、双向解析都稳定，开发首选）
        String str3 = "2026-11-04 23:40:15";
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time3 = LocalDateTime.parse(str3, dtf3);
        System.out.println("方式3解析结果：" + time3);
    }
}
