package theoryLesson.lesson15;

class Book2 {
    private String name;
    public Book2(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

public class GenericContainer<T> {
    private T value;

    public void save(T o) {
        this.value = o;
    }

    public T get() {
        return this.value;
    }

    public static void main(String[] args) {
        GenericContainer<Book2> container = new GenericContainer<>();
        container.save(new Book2("Java Programming"));
        Book2 v = container.get();
        System.out.println(v.getName());

        // String v = container.get();
        // 这一行直接编译报错
    }
}
