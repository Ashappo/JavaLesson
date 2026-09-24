package TheoryLesson.lesson06.sub;
import TheoryLesson.lesson06.DemoProtected;

public class SubDemo extends DemoProtected{
    void func(){
        System.out.println(num); // ✅子类继承，可直接访问父类protected成员
        show();                  // ✅子类继承，可直接访问父类protected方法

        DemoProtected father = new DemoProtected();
        // System.out.println(father.num); // ❌ new父类对象，不能访问protected
    }
}
