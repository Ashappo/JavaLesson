package ExperimentLesson.lesson04;

public class AssertionDemo {
    public static void main(String[] args) {
        System.out.println("===== 断言演示 =====");

        // 用法1：assert 条件表达式（条件为 true 时通过，无任何输出）
        int x = 10;
        assert x > 0;
        System.out.println("断言1通过：x > 0");

        // 用法2：assert 条件表达式 : 字符串型断言消息
        int age = 16;
        assert age >= 18 : "未满18岁，不允许注册，当前年龄：" + age;
        System.out.println("断言2通过：age >= 18");

        System.out.println("程序正常结束");
    }
}
