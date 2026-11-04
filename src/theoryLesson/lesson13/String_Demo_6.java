package theoryLesson.lesson13;

public class String_Demo_6 {
    public static void main(String[] args) {
        String file1 = "HelloWorld.java";
        String file2 = "note.jpg";
        System.out.println(isImage(file1));
        System.out.println(isImage(file2));
    }

    // 判断是否是图片类型的文件
    public static boolean isImage(String fileName){
        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
    }
}
