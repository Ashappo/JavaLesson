package TheoryLesson.lesson07.example2;

public class TestInit{
    public static void main(String[] args){
        System.out.println("------------------------------------");
        Teacher t1 = new Teacher();
        System.out.println("");

        System.out.println("------------------------------------");
        Teacher t2 = new Teacher("Tom");
        System.out.println("");

        System.out.println("------------------------------------");
        Teacher t3 = new Teacher("财务部",20);
    }
}

