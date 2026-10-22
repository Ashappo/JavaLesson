package ExperimentLesson.lesson04;

import java.io.IOException;

public class ThrowsThrowDemo {
    public static void main(String[] args) {
        // 调用1：调用声明了 throws 的方法，调用者必须处理
        try {
            System.out.println("10 / 0 = " + divide(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("main捕获到：" + e.getMessage());
        }

        // 调用2：方法内部用 throw 主动抛出异常对象
        try {
            checkScore(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("main捕获到：" + e.getMessage());
        }

        // 调用3：受检异常（checked）必须处理，否则编译不通过
        try {
            readFile("test.txt");
        } catch (IOException e) {
            System.out.println("main捕获到：" + e.getMessage());
        }

        System.out.println("程序正常结束");
    }

    // ① throws：声明本方法可能抛出异常，不在方法内处理，交给调用者
    public static int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }

    // ② throw：主动抛出异常对象（需要 new 异常对象）
    public static void checkScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("成绩必须在0~100之间，实际为：" + score);
        }
        System.out.println("成绩合法：" + score);
    }

    // ③ 受检异常：要么 try-catch，要么用 throws 向上声明
    public static void readFile(String path) throws IOException {
        throw new IOException("找不到文件：" + path);
    }
}
