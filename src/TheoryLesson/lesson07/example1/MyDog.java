package TheoryLesson.lesson07.example1;

public class MyDog{
    public static void main(String args[]){
        Dog myDog = new Dog();
        myDog.setWeight(50);
        System.out.println("My Dog's Weight is " + myDog.getWeight());
        myDog.bark();
    }
}

