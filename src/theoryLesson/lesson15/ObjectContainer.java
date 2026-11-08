package theoryLesson.lesson15;

class Book1 {
    private String name;
    public Book1(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

public class ObjectContainer {
    private Object value;

    public void save(Object o) {
        this.value = o;
    }

    public Object get() {
        return this.value;
    }

    public static void main(String[] args) {
        ObjectContainer container = new ObjectContainer();
        container.save(new Book1("Java Programming"));
        // 类型错误：编译通过，运行抛出 ClassCastException
        String v = (String) container.get();
        System.out.println(v);
    }
}

