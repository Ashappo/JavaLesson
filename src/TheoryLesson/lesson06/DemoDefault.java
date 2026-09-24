package TheoryLesson.lesson06;
class DemoDefault{
    String msg;
    void test(){
        System.out.println(msg); // ✅本类可以访问
    }
}
// 同包其他类：
// DemoDefault d = new DemoDefault();
// d.msg;    // ✅同包可以访问
// d.test(); // ✅同包可以访问

// 不同包（哪怕是子类）：
// DemoDefault d2 = new DemoDefault();
// d2.msg;   // ❌ 编译报错，跨包不能访问默认权限
