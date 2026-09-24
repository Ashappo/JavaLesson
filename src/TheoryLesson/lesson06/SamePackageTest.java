package TheoryLesson.lesson06;
public class SamePackageTest {
    public static void main(String[] args) {
        DemoDefault d1 = new DemoDefault();
        d1.msg = "同包测试";
        d1.test(); // ✅ 默认权限同包可访问

        DemoProtected d2 = new DemoProtected();
        d2.num = 100;
        d2.show(); // ✅ protected同包可访问

        DemoPublic d3 = new DemoPublic();
        d3.info = "public测试";
        d3.hello(); // ✅ public同包可访问

        DemoPrivate d4 = new DemoPrivate();
        // d4.name; // ❌ private，外部类访问失败
        // d4.say();// ❌ private，外部类访问失败
    }
}
