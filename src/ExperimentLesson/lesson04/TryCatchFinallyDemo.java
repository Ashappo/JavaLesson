package ExperimentLesson.lesson04;

public class TryCatchFinallyDemo {
    public static void main(String[] args) {
        System.out.println("===== 演示1：try块中发生异常 =====");
        try {
            int result = 10 / 0;                 // 除零，抛出 ArithmeticException
            System.out.println("result = " + result);   // 不会执行
        } catch (ArithmeticException e) {
            System.out.println("捕获异常：" + e.toString());
        } finally {
            System.out.println("finally块：无论是否发生异常都会执行");
        }
        System.out.println("异常处理完成，程序继续往下执行\n");

        System.out.println("===== 演示2：try块中无异常 =====");
        try {
            System.out.println("try块：正常执行");
        } catch (Exception e) {
            System.out.println("catch块：不会执行");
        } finally {
            System.out.println("finally块：仍然执行");
        }
        System.out.println();

        System.out.println("===== 演示3：finally与return的执行顺序 =====");
        System.out.println("testReturn() 返回：" + testReturn());
    }

    public static int testReturn() {
        int x = 10;
        try {
            return x;                  // ① 先确定返回值 x=10
        } finally {
            System.out.println("finally块先于return执行");
            x = 100;                   // ② 修改局部变量不影响已确定的返回值
            System.out.println("finally中修改x=" + x);
        }
    }
}
