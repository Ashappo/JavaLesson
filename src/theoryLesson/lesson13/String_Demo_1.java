package theoryLesson.lesson13;

public class String_Demo_1 {
    public String getString(String str){
        str = str + "-new";
        return str;
    }
    public static void main(String[] args) {
        String_Demo_1 sd = new String_Demo_1();
        String name = "Lucy";
        System.out.println("name: "  + name);
        System.out.println(sd.getString(name));
        System.out.println("name: " + name);

    }}

