package TheoryLesson.lesson07.example1;

public class Dog extends Animal{
    // 这里编译器默认生成 public Dog(){ super(); }
    // 但是父类Animal没有无参构造，代码直接编译失败！
    public int getWeight(){
        return 1000;
    }
    /**
     * 子类新增方法
     */
    public void bark(){
        System.out.println("Wang~~Wang~~~");
    }
}

