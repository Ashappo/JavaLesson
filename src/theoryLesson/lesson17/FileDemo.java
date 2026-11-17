package theoryLesson.lesson17;

import java.io.File;
public class FileDemo {
    public static void listAll(File dir) {
        File[] files = dir.listFiles();  // 注意：listFiles()
        if (files == null) return;      // 无权限或非目录
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("[目录] " + f.getName());
                listAll(f);             // 递归进入子目录
            } else {
                System.out.println("[文件] " + f.getName());
            }}}
    public static void main(String[] args) {
        listAll(new File("e:/javacode"));}}

