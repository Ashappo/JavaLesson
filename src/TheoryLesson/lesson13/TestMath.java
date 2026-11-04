package TheoryLesson.lesson13;

import java.util.Random;

public class TestMath{
    public static void main(String[] args){
        //创建Random对象，用于生成各种类型的随机数
        Random  ran = new Random();
        System.out.println("生成boolean类型的随机数：" + ran.nextBoolean());
        System.out.println("生成double类型的随机数：" + ran.nextDouble());
        System.out.println("生成float类型的随机数：" + ran.nextFloat());
        System.out.println("生成int类型的随机数：" + ran.nextInt());
        System.out.println("生成0到10之间的int类型的随机数：" + ran.nextInt(10));
        System.out.println("生成long类型的随机数：" + ran.nextLong());
    }
}
