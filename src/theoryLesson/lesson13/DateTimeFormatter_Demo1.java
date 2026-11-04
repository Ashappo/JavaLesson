package theoryLesson.lesson13;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatter_Demo1 {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();

        // ===================== 方式1：使用内置静态常量 =====================
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println("方式1（内置常量）：" + dtf1.format(date));

        // ===================== 方式2：FormatStyle枚举风格 =====================
        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("方式2（FormatStyle）：" + dtf2.format(date));

        // ===================== 方式3：自定义模式字符串（最常用） =====================
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("方式3（自定义模板）：" + dtf3.format(date));
    }
}

