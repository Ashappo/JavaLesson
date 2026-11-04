package theoryLesson.lesson14;

import java.util.LinkedList;

public class LinkedList_DemoQueue {
    public static void main(String[] args) {
        //通过LinkedList实现队列
        LinkedList team= new LinkedList();
        team.addFirst("A");
        team.addFirst("B");
        team.addFirst("C");
        team.addFirst("D");
        for(int i = 0; i<team.size();){
            System.out.print(team.removeLast() + "  ");
        }
    }
}
