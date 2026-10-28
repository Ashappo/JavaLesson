package TheoryLesson.lesson13;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] src = {10,20,30,40,50,60};
        int[] dest = {100,200,300,400,500,600};
        //实现数组元素的复制（拷贝）
        System.arraycopy(src, 0, dest, 3, 3);
        //显示复制的目标数组中的内容
        for(int data: dest) {
            System.out.print(data + "  ");
        }
    }

}
