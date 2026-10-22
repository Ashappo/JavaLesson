package TheoryLesson.lesson10;

public class Outer {
    private String outerField = "外部实例字段";
    private static String staticOuterField = "外部静态字段";

    private void outerMethod() {
        System.out.println("外部实例方法被调用");
    }

    // 1. 成员内部类
    class Inner {
        void show() {
            System.out.println("成员内部类访问: " + outerField);
            System.out.println("成员内部类访问静态字段: " + staticOuterField);
            outerMethod();
            System.out.println("Outer.this = " + Outer.this);
        }
    }

    // 2. 静态嵌套类
    static class StaticNested {
        void show() {
            System.out.println("静态嵌套类访问静态字段: " + staticOuterField);
            // System.out.println(outerField); // 编译错误：不能直接访问外部实例字段
        }
    }

    // 3. 局部内部类
    void localClassDemo() {
        final String localVar = "局部变量";

        class LocalInner {
            void show() {
                System.out.println("局部内部类访问局部变量: " + localVar);
                System.out.println("局部内部类访问外部实例字段: " + outerField);
            }
        }

        LocalInner localInner = new LocalInner();
        localInner.show();
    }

    // 4. 匿名内部类
    interface Greeting {
        void greet();
    }

    void anonymousClassDemo() {
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("匿名内部类问候，访问: " + outerField);
            }
        };

        greeting.greet();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();

        // 成员内部类实例化
        Outer.Inner inner = outer.new Inner();
        inner.show();

        // 静态嵌套类实例化
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.show();

        // 局部内部类
        outer.localClassDemo();

        // 匿名内部类
        outer.anonymousClassDemo();
    }
}
