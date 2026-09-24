package TheoryLesson.lesson07.example1;

class A{
    public A(){
        System.out.println("A类无参数的构造器。。。");
    }
    public A(int i){
        System.out.println("A类带参数的构造器。。。");
    }
}

class B extends A{
    public B(){
        System.out.println("B类无参数的构造器。。。 ");
    }
    public B(int i){
        System.out.println("B类带参数的构造器。。。");
    }
}

public class C extends B{
    public C(){
        System.out.println("C类无参数的构造器。。。 ");
    }

    public C(int i){
        this();
        System.out.println("C类有参数的构造器。。。 ");
    }

    public C(int i, int b){
        super(1);
        System.out.println("C类有2个参数的构造器。。。 ");
    }

    public static void main(String[] args){
        System.out.println("===== 创建 c1 = new C() =====");
        C c1 = new C();

        System.out.println("\n===== 创建 c2 = new C(1) =====");
        C c2 = new C(1);

        System.out.println("\n===== 创建 c3 = new C(1,2) =====");
        C c3 = new C(1,2);
    }
}

