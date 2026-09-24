package TheoryLesson.lesson06;
public class DemoPublic{
    public String info;
    public void hello(){
        System.out.println(info); // ✅本类可以访问
    }
}
// 任意包的类：
// DemoPublic d = new DemoPublic();
// d.info;   // ✅任意包都能访问
// d.hello();// ✅任意包都能访问
