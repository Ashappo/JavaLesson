package TheoryLesson.lesson06;
class DemoPrivate{
    private String name;
    private void say(){
        System.out.println(name); // ✅本类可以访问
    }
}
// DemoPrivate d = new DemoPrivate();
// d.name;   // ❌ 编译报错，外部无法访问
// d.say();  // ❌ 编译报错，外部无法访问
