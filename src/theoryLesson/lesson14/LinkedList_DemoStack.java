package theoryLesson.lesson14;

import java.util.LinkedList;

public class LinkedList_DemoStack {
    public static void main(String[] args) {
        //通过LinkedList实现栈
        LinkedList stack = new LinkedList();
        stack.addFirst("A");
        stack.addFirst("B");
        stack.addFirst("C");
        stack.addFirst("D");
        for(int i = 0; i<stack.size();){
            System.out.print(stack.removeFirst() + "  ");
        }
    }
}

