package theoryLesson.lesson13;

public class String_Demo_5 {
    public static void main(String[] args) {
        // 测试多组文件名：可以换成任意 xxx.xxx
        String[] fileNames = {"aa.bb.cc.jpg", "test.txt", "a.b.png", "data.xlsx", "readme"};

        for (String filename : fileNames) {
            // 找到最后一个小数点下标
            int lastDotIndex = filename.lastIndexOf(".");
            String newFilename;

            if (lastDotIndex != -1) {
                // 存在小数点：前缀改为abc，保留原后缀
                String suffix = filename.substring(lastDotIndex);
                newFilename = "abc" + suffix;
            } else {
                // 没有后缀的文件，直接命名为abc
                newFilename = "abc";
            }
            System.out.println("原文件名：" + filename + "  → 新文件名：" + newFilename);
        }
    }
}

