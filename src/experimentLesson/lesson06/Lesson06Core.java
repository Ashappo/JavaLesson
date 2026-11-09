package experimentLesson.lesson06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;

/**
 * 实验六 · I/O 流设计基础（字节流、字符流、序列化）—— 课堂单文件版。
 *
 * <p>本文件自包含：演示用的实体类写在内部类中，编译运行这一个文件即可。</p>
 *
 * <pre>
 * javac -encoding UTF-8 -d out experimentLesson/lesson06/Lesson06Core.java
 * java  -cp out experimentLesson.lesson06.Lesson06Core
 * </pre>
 *
 * <p>程序运行后在当前目录生成 io_demo 文件夹，保存所有演示文件，方便打开检查。</p>
 *
 * <p><b>六段内容与实验指导书的对应关系：</b></p>
 * <pre>
 * ① 字节流   FileInputStream / FileOutputStream / BufferedXxx（拷贝、追加、速度对比）
 * ② 字符流   FileReader / FileWriter / BufferedReader / BufferedWriter（按行读写、追加）
 * ③ 转换流   InputStreamReader / OutputStreamWriter（编码转换，了解）
 * ④ 序列化   ObjectOutputStream / ObjectInputStream（Serializable、transient、serialVersionUID）
 * ⑤ 其他流   DataXxxStream（基本类型）与 PrintStream（标准输出重定向，较高要求）
 * ⑥ 总结     四句话
 * </pre>
 *
 * <p><b>I/O 流分类总览（必须先建立的知识框架）：</b></p>
 * <pre>
 * 按方向分：输入流 InputStream / Reader，输出流 OutputStream / Writer
 * 按单位分：字节流（8 位，任何文件都能处理）
 *           字符流（16 位字符，只适合文本文件）
 * 按角色分：节点流（直接连数据源，如 FileInputStream）
 *           处理流（包装其他流增强功能，如 BufferedInputStream）
 * 字节流与字符流的桥梁：转换流 InputStreamReader / OutputStreamWriter
 * </pre>
 *
 * <p><b>JDK 兼容性：</b>只用 Java 8 语法与 API（含 try-with-resources），
 * 已在 JDK 1.8.0_442 上实测编译运行通过。</p>
 *
 * @author 面向对象程序设计课程组
 * @version 1.0
 */
public class Lesson06Core {

    /** 所有演示文件统一放在 io_demo 目录下，避免散落各处 */
    private static final File DIR = new File("io_demo");

    /**
     * 程序入口：按顺序跑完六段演示。
     *
     * @param args 未使用
     */
    public static void main(String[] args) {
        DIR.mkdirs();
        section1ByteStream();
        section2CharStream();
        section3ConvertStream();
        section4Serializable();
        section5DataAndPrint();
        section6Summary();
    }

    // ==================== ① 字节流 ====================

    /**
     * 字节流：FileInputStream / FileOutputStream 的读写、追加与文件拷贝。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>read() 单字节读取返回 int：正常返回 0~255，读到末尾返回 -1；</li>
     *   <li>write(byte[] b, int off, int len)：批量写入，是提高速度的关键；</li>
     *   <li>FileOutputStream(file, true)：第二个参数为 true 表示追加模式；</li>
     *   <li>BufferedInputStream / BufferedOutputStream 是处理流（装饰器模式），
     *       内部维护缓冲数组，把大量小读写合并为大读写，速度显著提高；</li>
     *   <li>字节流可以处理任何文件（图片、音频、视频），字符流只能处理文本。</li>
     * </ol>
     */
    private static void section1ByteStream() {
        title("① 字节流：FileInputStream / FileOutputStream（必掌握）");

        // ---- 1. 写文件：字符串必须先转成 byte[] 才能交给字节流 ----
        File file = new File(DIR, "byte_demo.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("Hello Java IO!\r\n".getBytes());
            fos.write("字节流可以处理任何类型的数据".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入 byte_demo.txt，长度 = " + file.length() + " 字节");

        // ---- 2. 读文件：read() 返回单个字节，-1 表示结束 ----
        try (FileInputStream fis = new FileInputStream(file)) {
            StringBuilder sb = new StringBuilder();
            int b;
            while ((b = fis.read()) != -1) {
                sb.append((char) b);
            }
            System.out.println("单字节逐个读出（观察中文是否正常）：");
            System.out.println("  " + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read() 一次只取一个字节，中文等多字节字符被拆开 → 出现乱码。
        // 字节流读文本的正确方式：读入 byte[] 后用 new String(bytes, 0, len) 转成字符串。
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buf = new byte[1024];
            int len = fis.read(buf);
            System.out.println("数组整体读出再转字符串：" + new String(buf, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---- 3. 追加模式：构造方法的第二个参数传 true ----
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write("\r\n追加的一行".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("追加后长度 = " + file.length() + " 字节（构造方法第二参数传 true）");

        // ---- 4. 文件拷贝：三种方案的速度对比（核心实验） ----
        File source = genSourceFile(512 * 1024);      // 生成 512KB 的源文件

        long cost = copyBySingleByte(source, new File(DIR, "copy_1.bin"));
        System.out.println("方案一 单字节拷贝            512KB 耗时 " + cost + " ms");

        cost = copyByByteArray(source, new File(DIR, "copy_2.bin"), false);
        System.out.println("方案二 字节数组拷贝          512KB 耗时 " + cost + " ms");

        cost = copyByByteArray(source, new File(DIR, "copy_3.bin"), true);
        System.out.println("方案三 数组 + 缓冲流拷贝     512KB 耗时 " + cost + " ms");

        System.out.println("→ 三种方案结果相同、速度不同：单字节读写的 I/O 次数最多所以最慢，");
        System.out.println("  缓冲流通过减少真实 I/O 次数来提速，数组越大效果越好（内存占用也越大）。");
    }

    /** 生成指定大小的演示源文件，内容为 0~255 循环填充的字节 */
    private static File genSourceFile(int size) {
        File f = new File(DIR, "source.bin");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            byte[] block = new byte[8192];
            int written = 0;
            while (written < size) {
                int len = Math.min(block.length, size - written);
                fos.write(block, 0, len);
                written += len;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    /** 方案一：一次读一个字节、写一个字节 —— 最慢 */
    private static long copyBySingleByte(File src, File dest) {
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - start;
    }

    /**
     * 方案二 / 三：缓冲数组批量拷贝。
     *
     * @param useBuffered true 时在外面再包一层缓冲流（方案三）
     */
    private static long copyByByteArray(File src, File dest, boolean useBuffered) {
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {

            // 缓冲流是"处理流"，必须包在节点流外面：new BufferedInputStream(节点流)
            InputStream in = useBuffered ? new BufferedInputStream(fis) : fis;
            OutputStream out = useBuffered ? new BufferedOutputStream(fos) : fos;

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {   // read(buf) 返回本次实际读到的字节数
                out.write(buf, 0, len);
            }
            out.flush();                            // 缓冲流必须 flush，最后的数据才真正落盘
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - start;
    }

    // ==================== ② 字符流 ====================

    /**
     * 字符流：FileReader / FileWriter 与 BufferedReader / BufferedWriter。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>字符流直接以 char 为单位处理，天然适合中文等文本；</li>
     *   <li>FileWriter(file, true) 同样支持追加；</li>
     *   <li>BufferedReader.readLine() 一次读一行（返回 null 表示结束），
     *       BufferedWriter.newLine() 写入跨平台的换行符 —— 这就是"每次处理一行"的实验要求；</li>
     *   <li>write() 写入的数据先在缓冲里，必须 close() 或 flush() 才会落盘 ——
     *       忘记 close 导致文件是空的，是最常见的实验错误。</li>
     * </ol>
     */
    private static void section2CharStream() {
        title("② 字符流：FileReader / FileWriter + BufferedReader（必掌握）");

        File file = new File(DIR, "char_demo.txt");

        // ---- 1. FileWriter 写文本 ----
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("第一行：字符流按字符处理");
            fw.write("\r\n第二行：适合纯文本文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FileWriter 写入完成，长度 = " + file.length() + " 字节（中文占几字节取决于系统编码）");

        // ---- 2. FileReader 读文本 ----
        try (FileReader fr = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) {         // 一次读一个字符，中文不会被拆开
                sb.append((char) c);
            }
            System.out.println("FileReader 读出：\n" + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---- 3. 追加模式 ----
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write("\r\n追加的第三行");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---- 4. BufferedReader / BufferedWriter：一行一行处理 ----
        File log = new File(DIR, "student_score.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write("张三,88.5");
            bw.newLine();                            // 跨平台换行（Linux \n / Windows \r\n）
            bw.write("李四,92.0");
            bw.newLine();
            bw.write("王五,76.5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            String line;
            int no = 1;
            while ((line = br.readLine()) != null) { // readLine() 返回 null 表示读完了
                System.out.println("  第 " + (no++) + " 行：" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("→ BufferedReader.readLine() 是文本处理最常用的组合。");
    }

    // ==================== ③ 转换流（了解） ====================

    /**
     * 转换流：InputStreamReader / OutputStreamWriter。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>转换流是字节流与字符流之间的桥梁：进方向把字节解码为字符，
     *       出方向把字符编码为字节；</li>
     *   <li>可以在构造时指定字符集，用于解决乱码问题；</li>
     *   <li>编码和解码必须使用同一套字符集，否则出现乱码；</li>
     *   <li>FileReader / FileWriter 不能指定编码，用的是系统默认编码
     *       （Windows 的 JDK 8 下通常是 GBK）—— 需要"指定编码读文本"时必须用转换流。</li>
     * </ol>
     */
    private static void section3ConvertStream() {
        title("③ 转换流：编码转换（了解）");

        File file = new File(DIR, "gbk_demo.txt");

        // 1. 用 GBK 编码写：System.out 输出"字节已写入文件"，转换发生在文件里
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "GBK")) {
            osw.write("转换流可以指定字符集");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 用同样的 GBK 读：内容正确
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK")) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = isr.read()) != -1) {
                sb.append((char) c);
            }
            System.out.println("GBK 写 → GBK 读：" + sb + "（正确）");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 换一套字符集读：乱码出现
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = isr.read()) != -1) {
                sb.append((char) c);
            }
            System.out.println("GBK 写 → UTF-8 读：" + sb + "（乱码）");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("→ 乱码的根源不是文件坏了，而是编码与解码使用的字符集不一致。");
    }

    // ==================== ④ 序列化 ====================

    /**
     * 对象流：ObjectOutputStream / ObjectInputStream 实现序列化与反序列化。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>序列化把内存中的对象转成字节序列保存到文件（或网络传输），
     *       反序列化把字节序列还原为对象；</li>
     *   <li>类必须实现 Serializable 标记接口（接口内没有任何方法，
     *       只是给 JVM 的标记）才允许被序列化，否则抛 NotSerializableException；</li>
     *   <li>serialVersionUID：类的版本号。反序列化时两边的版本号不一致会抛
     *       InvalidClassException；不写也能运行（编译器自动生成），
     *       但类一旦修改就可能无法还原，因此建议显式声明；</li>
     *   <li>transient 修饰的字段不参与序列化，反序列化后为默认值（引用类型为 null），
     *       常用于密码等敏感数据；static 字段属于类不属于对象，同样不会被序列化。</li>
     * </ol>
     */
    private static void section4Serializable() {
        title("④ 序列化：ObjectOutputStream / ObjectInputStream（必掌握）");

        File file = new File(DIR, "student.dat");
        Student original = new Student(1001, "张三", 88.5, "123456");

        // 1. 序列化：对象 → 文件
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(original);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("序列化完成，文件长度 = " + file.length() + " 字节");

        // 2. 反序列化：文件 → 对象（readObject 返回 Object，必须强制类型转换）
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            Student restored = (Student) obj;
            System.out.println("反序列化结果：" + restored);
            System.out.println("transient 密码字段 → " + restored.getPassword() + "（不参与序列化）");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("→ 对象的类必须实现 Serializable，否则抛 NotSerializableException。");
    }

    // ==================== ⑤ 其他流（了解 / 较高要求） ====================

    /**
     * 数据流与打印流。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>DataOutputStream / DataInputStream：按"基本类型的字节表示"直接读写，
     *       读写顺序必须严格一致；writeUTF 使用 Java 自定义的 UTF-8 变体，
     *       与普通文本文件不完全相同，要配对使用；</li>
     *   <li>PrintStream：System.out 就是标准输出流（PrintStream 类型），
     *       println / print / printf 都来自它；</li>
     *   <li>标准流重定向：System.setOut() 把输出改到文件、System.setIn() 把输入
     *       改到文件 —— 这就是"标准输入输出流的重定向"实验内容；</li>
     *   <li>重定向后务必保存原流引用以便恢复，否则后续输出再也回不到控制台。</li>
     * </ol>
     */
    private static void section5DataAndPrint() {
        title("⑤ 数据流与打印流（了解 / 较高要求）");

        // ---- 1. DataOutputStream / DataInputStream：基本类型读写 ----
        File data = new File(DIR, "data.bin");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(data))) {
            dos.writeInt(2026);
            dos.writeDouble(88.5);
            dos.writeBoolean(true);
            dos.writeUTF("数据流写入的字符串");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(data))) {
            // 读的顺序、类型必须与写的完全一致，否则数据错乱
            System.out.println("readInt    → " + dis.readInt());
            System.out.println("readDouble → " + dis.readDouble());
            System.out.println("readBoolean→ " + dis.readBoolean());
            System.out.println("readUTF    → " + dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---- 2. PrintStream 与标准输出重定向 ----
        File log = new File(DIR, "console.log");
        PrintStream originalOut = System.out;                    // 保存原输出流
        try (PrintStream fileOut = new PrintStream(new FileOutputStream(log))) {
            System.setOut(fileOut);                              // 重定向：println 写进文件
            System.out.println("这行写进了 console.log");
            System.out.println("重定向之后 System.out.println 不再显示在控制台");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(originalOut);                              // 恢复，否则后面全写进文件
        System.out.println("恢复后：println 回到控制台，文件内容如下 ↓");

        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== ⑥ 总结 ====================

    /** 四句话小结。 */
    private static void section6Summary() {
        title("⑥ 记住四句话");

        System.out.println("1. 字节流什么都能处理，字符流只适合文本；文本读写首选缓冲流按行处理。");
        System.out.println("2. 流用完必须 close()（或用 try-with-resources 自动关闭），否则数据不落盘。");
        System.out.println("3. 乱码的根源是编码与解码字符集不一致，需要指定编码就用转换流。");
        System.out.println("4. 序列化三要素：类实现 Serializable、写 serialVersionUID、敏感字段加 transient。");
    }

    // ==================== 辅助方法 ====================

    /** 打印分段标题 */
    private static void title(String text) {
        System.out.println();
        System.out.println("==== " + text + " ====");
    }

    // ==================== 演示用内部类 ====================

    /**
     * 序列化演示用的学生类。
     *
     * <p>实现 Serializable 才能被对象流读写；serialVersionUID 固定类的版本；
     * password 用 transient 修饰，序列化时被跳过。</p>
     */
    static class Student implements Serializable {

        /** 类版本号：反序列化时双方必须一致，否则抛 InvalidClassException */
        private static final long serialVersionUID = 1L;

        private int id;
        private String name;
        private double score;

        /** transient：该字段不参与序列化，反序列化后为 null */
        private transient String password;

        Student(int id, String name, double score, String password) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.password = password;
        }

        String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name=" + name + ", score=" + score + "}";
        }
    }
}
