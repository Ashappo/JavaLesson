package ExperimentLesson.lesson04;

public class CustomExceptionDemo {
    public static void main(String[] args) {
        // 用法1：单独处理自定义异常
        try {
            checkScore(150);
        } catch (ScoreException e) {
            System.out.println("捕获自定义异常：" + e.getMessage());
        }
        System.out.println();

        // 用法2：多个catch，子类在前，父类最后
        System.out.println("===== 多catch演示 =====");
        test(0);   // 触发 ArithmeticException
        test(1);   // 触发 NumberFormatException
        test(2);   // 触发 ScoreException（自定义异常）
        test(3);   // 无异常
    }

    // throw：抛出 自定义异常对象；throws：向调用者声明
    public static void checkScore(int score) throws ScoreException {
        if (score < 0 || score > 100) {
            throw new ScoreException("成绩" + score + "超出0~100的范围");
        }
        System.out.println("成绩合法：" + score);
    }

    public static void test(int flag) {
        try {
            switch (flag) {
                case 0:
                    System.out.println(10 / 0);                 // ArithmeticException
                    break;
                case 1:
                    System.out.println(Integer.parseInt("abc"));// NumberFormatException
                    break;
                case 2:
                    checkScore(150);                            // ScoreException（受检，必须处理）
                    break;
                case 3:
                    System.out.println("无异常，正常执行");
                    break;
            }
        } catch (ArithmeticException e) {      // 子类在前
            System.out.println("算术异常：" + e.getMessage());
        } catch (NumberFormatException e) {    // 子类在前
            System.out.println("数字格式异常：" + e.getMessage());
        } catch (ScoreException e) {           // 自定义异常
            System.out.println("自定义异常：" + e.getMessage());
        } catch (Exception e) {                // 父类型放最后（兜底）
            System.out.println("其他异常：" + e);
        } finally {
            System.out.println("  -- finally总是执行");
        }
    }
}

