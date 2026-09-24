package TheoryLesson.lesson06;
public class DemoProtected{
    protected int num;
    protected void show(){
        System.out.println(num); // ✅本类可以访问
    }
}
// 同包其他类：
// DemoProtected d = new DemoProtected();
// d.num;   // ✅同包可以访问
// d.show();// ✅同包可以访问

// 不同包子类中：
// DemoProtected d2 = new DemoProtected();
// d2.num;  // ❌ 不能new父类对象直接访问protected
// 但子类继承后直接使用 num ✅
