package theoryLesson.lesson17;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
public class CharsetDemo {
    public static void main(String[] args) {
        String msg = "计算机科学与技术";
        Charset gbk = Charset.forName("GBK");
        // 编码：字符串 → 字节序列（可观察长度差异）
        byte[] b1 = msg.getBytes(gbk);
        byte[] b2 = msg.getBytes(StandardCharsets.UTF_8);
        System.out.println("GBK 长度: " + b1.length);
        System.out.println("UTF-8 长度: " + b2.length);
        // 解码：字符集一致 → 正常还原
        System.out.println(new String(b1, gbk));
        // 解码：字符集不一致 → 乱码
        System.out.println(new String(b1,
                StandardCharsets.UTF_8));      // 乱码
        System.out.println(new String(b2,
                StandardCharsets.UTF_8));      // 乱码
    }}

